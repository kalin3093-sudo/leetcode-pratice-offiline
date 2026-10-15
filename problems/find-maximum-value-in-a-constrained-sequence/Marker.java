import java.util.*;
class Marker {
    public int findMaxVal(int n, int[][] restrictions, int[] diff) {
        int[] bound = new int[n];
        Arrays.fill(bound, Integer.MAX_VALUE);
        bound[0] = 0;
        for (int[] r : restrictions) {
            bound[r[0]] = r[1];
        }
        for (int i = 1; i < n; i++) {
            bound[i] = Math.min(bound[i], bound[i - 1] + diff[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            bound[i] = Math.min(bound[i], bound[i + 1] + diff[i]);
        }
        int ans = 0;
        for (int b : bound) ans = Math.max(ans, b);
        return ans;
    }

    public boolean isCorrect(int n, int[][] restrictions, int[] diff, int output) {
        return findMaxVal(n, restrictions, diff) == output;
    }
}