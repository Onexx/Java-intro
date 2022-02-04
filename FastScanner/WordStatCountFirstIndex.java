import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

class WordChecker implements FastScanner.Checker {
    @Override
    public boolean check(char ch) {
        return Character.getType(ch) == Character.DASH_PUNCTUATION ||
                Character.isLetter(ch) || ch == '\'';
    }
}

public class WordStatCountFirstIndex {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Incorrect arguments");
            return;
        }

        Map<String, Tuple> words = new LinkedHashMap<>();
        WordChecker checker = new WordChecker();

        try (FastScanner scanner = new FastScanner(new File(args[0]), StandardCharsets.UTF_8, checker)) {
            for (int lineNumber = 0; scanner.inputNotEmpty(); lineNumber++) {
                int cnt = 1;
                while (!scanner.lineEndReached() && scanner.hasNext()) {
                    String word = scanner.nextWord();
                    // :NOTE: Слишком много обращений в Map
                    Tuple entry = words.get(word);
                    if (entry != null) {
                        entry.incOccurrences();
                        entry.pushBack(cnt, lineNumber);
                    } else {
                        words.put(word, new Tuple());
                        words.get(word).pushBack(cnt,lineNumber);
                    }
                    cnt++;
                }
                scanner.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.printf("Input file not found: %s%n", e.getMessage());
            return;
        } catch (IOException e) {
            System.err.printf("Input Error: %s%n", e.getMessage());
            return;
        }

        List<Map.Entry<String, Tuple>> list = new ArrayList<>(words.entrySet());
        list.sort(Comparator.comparing(o -> o.getValue().getOccurrences()));
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            for (Map.Entry<String, Tuple> entry : list) {
                String word = entry.getKey();
                writer.write(word + " " + entry.getValue().getOccurrences());
                int size = words.get(word).size();
                for (int i = 0; i < size; i++) {
                    writer.write(" " + words.get(word).get(i));
                }
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.printf("Output file not found: %s%n", e.getMessage());
        } catch (IOException e) {
            System.err.printf("Output error: %s%n", e.getMessage());
        }
    }
}