package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Incorrect arguments");
            return;
        }
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            StringBuilder paragraph = new StringBuilder();
            while (line != null) {
                while (line != null && !line.isEmpty()) {
                    paragraph.append(line).append("\n");
                    line = reader.readLine();
                }
                if (paragraph.length() != 0) {
                    paragraph.setLength(paragraph.length() - 1);
                    new ParagraphParser(paragraph).toHtml(result);
                    result.append("\n");
                    paragraph = new StringBuilder();
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found" + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Input error:" + e.getMessage());
            return;
        }
        try {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                writer.write(result.toString());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Write error: " + e.getMessage());
        }
    }
}
