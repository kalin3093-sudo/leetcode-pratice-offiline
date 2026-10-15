import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int specialTriplets(int[] nums) {
        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);
        int[] freqPrev = new int[maxVal + 1];
        int[] freqNext = new int[maxVal + 1];
        for (int x : nums) freqNext[x]++;

        long ans = 0;
        for (int j = 0; j < nums.length; j++) {
            int val = nums[j];
            freqNext[val]--;
            int target = val * 2;
            if (target <= maxVal) {
                ans = (ans + 1L * freqPrev[target] * freqNext[target]) % MOD;
            }
            freqPrev[val]++;
        }
        return (int) ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == specialTriplets(nums);
    }
}