import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReverseAvg {
    public static void main(String args[]) {
        Scanner lineScanner = new Scanner(System.in);
        List<int[]> ints = new ArrayList<>();
        int m = 0;
        int n;
        for (n = 0; lineScanner.hasNextLine(); n++) {
            Scanner scanner = new Scanner(lineScanner.nextLine());
            int[] row = new int[1];
            int ptr = 0;
            while (scanner.hasNextInt()) {
                if (ptr == row.length) {
                    row = Arrays.copyOf(row, row.length * 2);
                }
                row[ptr++] = scanner.nextInt();
            }
            m = Math.max(m, row.length);
            ints.add(Arrays.copyOf(row, ptr));
        }

        long[] dx = new long[m];
        long[] dy = new long[n];
        int[] cntX = new int[m];
        int[] cntY = new int[n];
        for (int i = 0; i < ints.size(); i++) {
            int[] row = ints.get(i);
            for (int j = 0; j < row.length; j++) {
                dy[i] += row[j];
                cntY[i]++;
                dx[j] += row[j];
                cntX[j]++;
            }
        }

        for (int i = 0; i < ints.size(); i++) {
            for (int j = 0; j < ints.get(i).length; j++) {
                long ans = (dx[j] + dy[i] - ints.get(i)[j]) / (cntX[j] + cntY[i] - 1);
                System.out.print(ans + " ");
            }
            System.out.println();
        }
    }
}