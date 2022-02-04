import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WordStatCountShingles {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Incorrect filenames");
            return;
        }
        Map<String, Integer> words = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                StringBuilder builder = new StringBuilder();
                ch = Character.toLowerCase(ch);
                while (Character.getType(ch) == Character.DASH_PUNCTUATION ||
                        Character.isLetter(ch) || ch == '\'') {
                    builder.append((char) ch);
                    if (builder.length() == 3) {
                        String shingle = builder.toString();
                        if (words.get(shingle) != null) {
                            words.replace(shingle, words.get(shingle) + 1);
                        } else {
                            words.put(shingle, 1);
                        }
                        builder.deleteCharAt(0);
                    }
                    ch = reader.read();
                    ch = Character.toLowerCase(ch);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.printf("Input file not found: %s%s", e.getMessage(), System.lineSeparator());
        } catch (IOException e) {
            System.err.printf("Input Error: %s%s", e.getMessage(), System.lineSeparator());
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
        list.sort(Map.Entry.comparingByValue());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
            for (Map.Entry entry : list) {
                writer.write(String.format("%s %s", entry.getKey(), entry.getValue()));
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.err.printf("Output file not found: %s%s", e.getMessage(), System.lineSeparator());
        } catch (IOException e) {
            System.err.printf("Output error: %s%s" + e.getMessage(), System.lineSeparator());
        }
    }
}