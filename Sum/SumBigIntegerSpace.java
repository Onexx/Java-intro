import java.math.BigInteger;

public class SumBigIntegerSpace {
    public static void main(String[] args) {
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < args.length; i++) {
            int beginIndex = -1;
            boolean isNumber = false;
            for (int j = 0; j <= args[i].length(); j++) {
                if (j == args[i].length() || Character.getType(args[i].charAt(j)) == Character.SPACE_SEPARATOR) {
                    if (beginIndex != -1 && isNumber) {
                        BigInteger currentNumber;
                        currentNumber = new BigInteger(args[i].substring(beginIndex, j));
                        sum = sum.add(currentNumber);
                    }
                    isNumber = false;
                } else {
                    if (!isNumber) {
                        beginIndex = j;
                    }
                    isNumber = true;
                }
            }
        }
        System.out.println(sum);
    }
}