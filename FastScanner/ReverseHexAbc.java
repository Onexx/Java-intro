import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

class ReverseChecker implements FastScanner.Checker {
    @Override
    public boolean check(char c) {
        return !Character.isWhitespace(c);
    }
}

public class ReverseHexAbc {
    public static void main(String args[]) {
        ReverseChecker checker = new ReverseChecker();
        List<int[]> ints = new ArrayList<>();
        int m = 0;
        int n = 0;

        try (FastScanner scanner = new FastScanner(System.in, StandardCharsets.UTF_8, checker)) {
            for (; scanner.inputNotEmpty(); ) {
                int[] row = new int[1];
                int ptr = 0;
                boolean lineEndReached = scanner.LineEndReached();
                while (!lineEndReached && scanner.hasNext()) {
                    if (ptr == row.length) {
                        row = Arrays.copyOf(row, row.length * 2);
                    }
                    try {
                        row[ptr++] = scanner.nextHex();
                    } catch (NumberFormatException e) {
                        System.err.printf("Couldn't convert to number: %s%n", e.getMessage());
                    } catch (NoSuchElementException e) {
                        System.err.println(e.getMessage());
                    }
                    lineEndReached = scanner.LineEndReached();
                }
                if (lineEndReached) {
                    scanner.newLine();
                    m = Math.max(m, row.length);
                    ints.add(Arrays.copyOf(row, ptr));
                    n++;
                }
            }
        } catch (IOException e) {
            System.err.printf("Input error: %s%n", e.getMessage());
        }catch (IllegalStateException e){
            System.err.println(e.getMessage());
        }

        for (int i = ints.size() - 1; i >= 0; i--) {
            for (int j = ints.get(i).length - 1; j >= 0; j--) {
                long ans = ints.get(i)[j];
                System.out.printf("%d ", ans);
            }
            System.out.println();
        }
    }
}