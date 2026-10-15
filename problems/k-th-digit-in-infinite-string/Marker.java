import java.util.*;
class Marker {
    public int kthDigit(long k) {
        long b = 0, digitsBefore = 0;
        while (true) {
            long blockDigits = digitsInBlock(b);
            if (digitsBefore + blockDigits >= k) break;
            digitsBefore += blockDigits;
            b++;
        }
        long posInBlock = k - digitsBefore - 1; // 0-indexed
        if (b == 0) {
            // Block 0: 1-9 (9 numbers, 1 digit each)
            int num = 1 + (int) posInBlock;
            return Character.getNumericValue(Integer.toString(num).charAt(0));
        }
        // Block b > 0: 10 numbers, each has digit length of (10*b + 9)
        int digitLen = String.valueOf(10L * b + 9).length();
        long numIdx = posInBlock / digitLen;
        int digitIdx = (int) (posInBlock % digitLen);
        long num;
        if (b % 2 == 0) {
            num = 10L * b + numIdx;
        } else {
            num = 10L * b + 9 - numIdx;
        }
        return Character.getNumericValue(Long.toString(num).charAt(digitIdx));
    }

    private long digitsInBlock(long b) {
        if (b == 0) return 9; // 1-9
        int digitLen = String.valueOf(10L * b + 9).length();
        return 10L * digitLen;
    }

    public boolean isCorrect(long k, int output) {
        return output == kthDigit(k);
    }
}