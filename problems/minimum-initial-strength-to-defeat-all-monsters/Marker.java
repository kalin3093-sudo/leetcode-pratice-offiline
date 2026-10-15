import java.util.*;
class Marker {
    public long minInitialStrength(int[] monsters, int[][] boosts) {
        int n = monsters.length;
        long[] diff = new long[n + 1];
        for (int[] b : boosts) {
            diff[b[0]] += b[2];
            diff[b[1] + 1] -= b[2];
        }
        long ans = 0, prefix = 0, bonus = 0;
        for (int i = 0; i < n; i++) {
            bonus += diff[i];
            if (bonus < monsters[i]) {
                ans = Math.max(ans, prefix + monsters[i] - bonus);
            }
            prefix += monsters[i];
        }
        return ans;
    }

    public boolean isCorrect(int[] monsters, int[][] boosts, long output) {
        long expected = minInitialStrength(monsters, boosts);
        return expected == output;
    }
}
