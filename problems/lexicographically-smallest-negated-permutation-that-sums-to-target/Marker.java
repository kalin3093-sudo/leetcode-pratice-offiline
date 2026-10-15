import java.util.*;
class Marker {
    public int[] lexSmallestNegatedPerm(int n, long target) {
        long S = 1L * n * (n + 1) / 2;
        if (target < -S || target > S || (S - target) % 2 == 1) {
            return new int[0];
        }
        long D = S - target;
        boolean[] neg = new boolean[n + 1];
        for (int x = n; x >= 1; x--) {
            if (2L * x <= D) {
                neg[x] = true;
                D -= 2L * x;
            }
        }
        int[] ans = new int[n];
        int idx = 0;
        for (int x = 1; x <= n; x++) {
            ans[idx++] = neg[x] ? -x : x;
        }
        Arrays.sort(ans);
        return ans;
    }

    public boolean isCorrect(int n, long target, int[] output) {
        if (output == null) return false;
        int[] expected = lexSmallestNegatedPerm(n, target);
        return Arrays.equals(expected, output);
    }
}