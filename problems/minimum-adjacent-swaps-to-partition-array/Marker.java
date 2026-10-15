import java.util.*;
class Marker {
    public int minAdjacentSwaps(int[] nums, int a, int b) {
        final int MOD = 1_000_000_007;
        int n = nums.length;
        int[] groups = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x < a) groups[i] = 0;
            else if (x <= b) groups[i] = 1;
            else groups[i] = 2;
        }
        long inv = 0;
        int[] cnt = new int[3];
        for (int g : groups) {
            for (int k = g + 1; k < 3; k++) {
                inv += cnt[k];
            }
            cnt[g]++;
        }
        return (int) (inv % MOD);
    }

    public boolean isCorrect(int[] nums, int a, int b, int output) {
        int expected = minAdjacentSwaps(nums, a, b);
        return expected == output;
    }
}