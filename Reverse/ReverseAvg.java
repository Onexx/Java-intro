import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReverseAvg {
    public static void main(String args[]) {
        Scanner lineScanner = new Scanner(System.in);
        List<int[]> ints = new ArrayList<>();
        int m = 0;
        int n;
        for (n = 0; lineScanner.hasNextLine(); n++) {
            String line = lineScanner.nextLine();
            Scanner scanner = new Scanner(line);
            int[] row = new int[1];
            int ptr = 0;
            while (scanner.hasNextInt()) {
                if (ptr == row.length) {
                    int[] newRow = new int[row.length * 2];
                    System.arraycopy(row, 0, newRow, 0, row.length);
                    row = newRow;
                }
                row[ptr] = scanner.nextInt();
                ptr++;
            }
            int[] newRow = new int[ptr];
            System.arraycopy(row, 0, newRow, 0, ptr);
            row = newRow;
            m = Math.max(m, row.length);
            ints.add(row);
        }
        long[] dx = new long[m];
        long[] dy = new long[n];
        int[] cntx = new int[m];
        int[] cnty = new int[n];
        for (int i = 0; i < ints.size(); i++) {
            int[] row = ints.get(i);
            for (int j = 0; j < row.length; j++) {
                dy[i] += row[j];
                cnty[i]++;
                dx[j] += row[j];
                cntx[j]++;
            }
        }

        for (int i = 0; i < ints.size(); i++) {
            for (int j = 0; j < ints.get(i).length; j++) {
                long ans = (dx[j] + dy[i] - ints.get(i)[j]) / (cntx[j] + cnty[i] - 1);
                System.out.print(ans + " ");
            }
            System.out.print('\n');
        }
    }
}