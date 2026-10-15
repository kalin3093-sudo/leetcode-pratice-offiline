import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        int minVal = complexity[0];
        for (int i = 1; i < n; i++) {
            if (complexity[i] <= minVal) return 0;
        }
        long ans = 1;
        for (int i = 2; i < n; i++) {
            ans = ans * i % MOD;
        }
        return (int) ans;
    }

    public boolean isCorrect(int[] complexity, int output) {
        return output == countPermutations(complexity);
    }
}