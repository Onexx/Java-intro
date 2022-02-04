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

public class WordStatCountShingles {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Incorrect filenames");
            return;
        }
        Map<String, Integer> words = new LinkedHashMap<>();
        WordChecker checker = new WordChecker();
        try (FastScanner scanner = new FastScanner(new File(args[0]), StandardCharsets.UTF_8, checker)) {
            while (scanner.hasNext()) {
                try {
                    String word = scanner.nextWord();
                    if (word.length() < 3) {
                        continue;
                    }
                    for (int i = 0; i < word.length() - 2; i++) {
                        String shingle = word.substring(i, i + 3);
                        if (words.get(shingle) != null) {
                            words.replace(shingle, words.get(shingle) + 1);
                        } else {
                            words.put(shingle, 1);
                        }
                    }
                } catch (NoSuchElementException | IOException | IllegalStateException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.printf("Input file not found: %s%n", e.getMessage());
        } catch (IOException e) {
            System.err.printf("Input Error: %s%n", e.getMessage());
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
        list.sort(Map.Entry.comparingByValue());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            for (Map.Entry entry : list) {
                writer.write(String.format("%s %s", entry.getKey(), entry.getValue()));
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.printf("Output file not found: %s%n", e.getMessage());
        } catch (IOException e) {
            System.err.printf("Output error: %s%n", e.getMessage());
        }
    }
}