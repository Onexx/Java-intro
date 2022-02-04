package md2html;

import java.util.*;

public class TextParser {

    StringBuilder source;

    static private final Map<String, String> md2HtmlMarks = Map.of(
            "*", "em",
            "_", "em",
            "**", "strong",
            "__", "strong",
            "--", "s",
            "`", "code"
    );

    static private final Map<Character, String> specialSymbols = Map.of(
            '<', "&lt;",
            '>', "&gt;",
            '&', "&amp;"
    );

    static private final Map<String, String> closeToOpen = Map.of(
            ")", "(",
            "]", "["
    );

    public TextParser(StringBuilder source) {
        this.source = source;
    }

    private static boolean isMarker(String string) {
        return md2HtmlMarks.containsKey(string) || closeToOpen.containsKey(string) || closeToOpen.containsValue(string);
    }

    private static String getMarker(StringBuilder source, int pos) {
        for (int size = 2; size > 0; size--) {
            if (pos + size <= source.length()) {

                final String marker = source.substring(pos, pos + size);
                if (isMarker(marker)) {
                    return closeToOpen.getOrDefault(marker, marker);
                }
            }
        }
        return null;
    }

    void toHtml(StringBuilder result) {
        Map<Integer, Integer> markers = new HashMap<>();
        Map<String, Integer> openingMarkerPos = new HashMap<>();
        for (int i = 0; i < source.length(); i++) {
            while (source.charAt(i) == '\\') {
                i += 2;
            }
            String marker = getMarker(source, i);
            if (marker != null) {
                if (openingMarkerPos.containsKey(marker)) {
                    markers.put(openingMarkerPos.get(marker), i);
                    openingMarkerPos.remove(marker);
                } else {
                    openingMarkerPos.put(marker, i);
                }
            }
        }
        parse(result, markers, 0, source.length());
    }

    boolean isLink(Map<Integer, Integer> markers, int pos) {
        return source.charAt(pos) == '[' &&
                source.charAt(markers.get(pos) + 1) == '(' &&
                markers.containsKey(markers.get(pos) + 1);
    }

    void parse(StringBuilder result, Map<Integer, Integer> markers, int l, int r) {
        for (int i = l; i < r; i++) {
            if (markers.containsKey(i)) {
                String marker = getMarker(source, i);
                int closePosition = markers.get(i);
                if (md2HtmlMarks.containsKey(marker)) {
                    String htmlMarker = md2HtmlMarks.get(marker);
                    result.append("<").append(htmlMarker).append(">");
                    parse(result, markers, i + marker.length(), closePosition);
                    result.append("</").append(htmlMarker).append(">");
                    i = closePosition + marker.length() - 1;
                } else if (isLink(markers, i)) {
                    result.append("<a href='");
                    result.append(source, closePosition + 2, markers.get(closePosition + 1));
                    result.append("'>");
                    parse(result, markers, i + 1, closePosition);
                    result.append("</a>");
                    i = markers.get(closePosition + 1);
                } else {
                    result.append(source.charAt(i));
                }
            } else if (specialSymbols.containsKey(source.charAt(i))) {
                result.append(specialSymbols.get(source.charAt(i)));
            } else if (source.charAt(i) != '\\') {
                result.append(source.charAt(i));
            }
        }
    }
}
