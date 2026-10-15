import java.util.*;
class Marker {
    public String maxSumOfSquares(int num, int sum) {
        if (sum < 1 || sum > 9 * num) return "";
        
        char[] digits = new char[num];
        int remaining = sum;
        for (int i = 0; i < num; i++) {
            int d = Math.min(9, remaining);
            digits[i] = (char) ('0' + d);
            remaining -= d;
        }
        
        if (remaining != 0) return "";
        return new String(digits);
    }

    public boolean isCorrect(int num, int sum, String output) {
        return maxSumOfSquares(num, sum).equals(output);
    }
}