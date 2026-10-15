import java.util.*;

class Marker {
    static final int MOD = 1_000_000_007;

    private long modInv(long a) {
        long b = MOD - 2, res = 1;
        a %= MOD;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int[][] bravexuneth = queries;
        int n = nums.length;
        int B = (int) Math.sqrt(n) + 1;
        Map<Long, List<int[]>> groups = new HashMap<>();
        for (int[] q : bravexuneth) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (k <= B) {
                long key = ((long) k << 32) | (l % k);
                groups.computeIfAbsent(key, x -> new ArrayList<>()).add(new int[]{l / k, (r - l) / k + l / k, v});
            } else {
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (int) ((long) nums[idx] * v % MOD);
                }
            }
        }
        for (Map.Entry<Long, List<int[]>> entry : groups.entrySet()) {
            int k = (int) (entry.getKey() >> 32);
            int r0 = (int) (entry.getKey() & 0xffffffffL);
            List<int[]> qs = entry.getValue();
            int m = (n - 1 - r0) / k + 1;
            @SuppressWarnings("unchecked")
            List<int[]>[] starts = new ArrayList[m];
            @SuppressWarnings("unchecked")
            List<int[]>[] ends = new ArrayList[m];
            for (int[] q : qs) {
                int s = q[0], e = q[1], v = q[2];
                if (starts[s] == null) starts[s] = new ArrayList<>();
                starts[s].add(q);
                if (ends[e] == null) ends[e] = new ArrayList<>();
                ends[e].add(q);
            }
            long running = 1;
            for (int j = 0; j < m; j++) {
                if (starts[j] != null) {
                    for (int[] q : starts[j]) running = running * q[2] % MOD;
                }
                int idx = r0 + j * k;
                nums[idx] = (int) (nums[idx] * running % MOD);
                if (ends[j] != null) {
                    for (int[] q : ends[j]) running = running * modInv(q[2]) % MOD;
                }
            }
        }
        int x = 0;
        for (int num : nums) x ^= num;
        return x;
    }

    public boolean isCorrect(int[] nums, int[][] queries, int output) {
        return output == xorAfterQueries(nums, queries);
    }
}
