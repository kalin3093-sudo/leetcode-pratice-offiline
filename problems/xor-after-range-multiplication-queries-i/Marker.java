import java.util.*;

class Marker {
    static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int idx = l; idx <= r; idx += k) {
                nums[idx] = (int) ((long) nums[idx] * v % MOD);
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
