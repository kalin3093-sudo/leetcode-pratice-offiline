import java.util.*;

public class Marker {
    static final int MOD = 1_000_000_007;

    public int countValidSubsets(int[] parent, int[] nums, int k) {
        int n = parent.length;
        List<List<Integer>> ch = new ArrayList<>();
        for (int i = 0; i < n; i++) ch.add(new ArrayList<>());
        for (int i = 1; i < n; i++) ch.get(parent[i]).add(i);

        int[][] dp0 = new int[n][k];
        int[][] dp1 = new int[n][k];
        for (int u = n - 1; u >= 0; u--) {
            int[] acc0 = new int[k];
            int[] acc1 = new int[k];
            acc0[0] = 1;
            acc1[nums[u] % k] = 1;
            for (int c : ch.get(u)) {
                int[] na0 = new int[k];
                int[] na1 = new int[k];
                for (int a = 0; a < k; a++) {
                    if (acc0[a] == 0 && acc1[a] == 0) continue;
                    for (int b = 0; b < k; b++) {
                        int free = dp0[c][b];
                        if (free == 0) continue;
                        int nb = (a + b) % k;
                        if (acc0[a] != 0) na0[nb] = (int) ((long) na0[nb] + (long) acc0[a] * free % MOD) % MOD;
                        if (acc1[a] != 0) na1[nb] = (int) ((long) na1[nb] + (long) acc1[a] * free % MOD) % MOD;
                    }
                    for (int b = 0; b < k; b++) {
                        int taken = dp1[c][b];
                        if (taken == 0) continue;
                        int nb = (a + b) % k;
                        if (acc0[a] != 0) na0[nb] = (int) ((long) na0[nb] + (long) acc0[a] * taken % MOD) % MOD;
                    }
                }
                acc0 = na0;
                acc1 = na1;
            }
            dp0[u] = acc0;
            dp1[u] = acc1;
        }
        long ans = ((long) dp0[0][0] + dp1[0][0] - 1) % MOD;
        return (int) ((ans + MOD) % MOD);
    }

    public boolean isCorrect(int[] parent, int[] nums, int k, int out) {
        return countValidSubsets(parent, nums, k) == out;
    }
}
