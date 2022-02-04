package md2html;

public class ParagraphParser {

    StringBuilder source;

    public ParagraphParser(StringBuilder source) {
        this.source = source;
    }

    private int headerLevel() {
        int ptr = 0;
        while (ptr < source.length() && source.charAt(ptr) == '#') {
            ptr++;
        }
        if (ptr != source.length() && source.charAt(ptr) == ' ' && ptr > 0) {
            return ptr;
        } else {
            return 0;
        }
    }

    public void toHtml(StringBuilder result) {
        int headerLevel = headerLevel();
        if (headerLevel != 0) {
            result.append("<h").append(headerLevel).append(">");
            new TextParser(new StringBuilder(source.substring(headerLevel + 1))).toHtml(result);
            result.append("</h").append(headerLevel).append(">");
        } else {
            result.append("<p>");
            new TextParser(source).toHtml(result);
            result.append("</p>");
        }
    }

}
