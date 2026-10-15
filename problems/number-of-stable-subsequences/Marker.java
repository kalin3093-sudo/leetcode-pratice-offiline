import java.util.*;

class Marker {
    public int countStableSubsequences(int[] nums) {
        final int MOD = 1_000_000_007;
        long o1 = 0, o2 = 0, e1 = 0, e2 = 0;
        for (int x : nums) {
            if ((x & 1) == 1) {
                long no1 = (o1 + e1 + e2 + 1) % MOD;
                long no2 = (o2 + o1) % MOD;
                o1 = no1;
                o2 = no2;
            } else {
                long ne1 = (e1 + o1 + o2 + 1) % MOD;
                long ne2 = (e2 + e1) % MOD;
                e1 = ne1;
                e2 = ne2;
            }
        }
        return (int) ((o1 + o2 + e1 + e2) % MOD);
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == countStableSubsequences(nums);
    }
}
