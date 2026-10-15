import java.util.*;
class Marker {
    public boolean isDigitorialPermutation(int n) {
        int[] fact = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        int sum = 0;
        for (int m = n; m > 0; m /= 10) sum += fact[m % 10];
        return norm(sum).equals(norm(n));
    }

    private static String norm(int x) {
        char[] c = String.valueOf(x).toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    public boolean isCorrect(int n, boolean output) {
        return isDigitorialPermutation(n) == output;
    }
}
