import java.util.*;
class Marker {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        if (n == 0) return new int[0];
        int[] prefixMax = new int[n];
        int[] suffixMin = new int[n];
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);

        // Find cuts: between i and i+1 is a cut if prefixMax[i] <= suffixMin[i+1]
        boolean[] isCut = new boolean[n - 1];
        for (int i = 0; i < n - 1; i++) {
            if (prefixMax[i] <= suffixMin[i + 1]) isCut[i] = true;
        }

        int[] ans = new int[n];
        int compStart = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || isCut[i]) {
                // Component is from compStart to i
                int compMax = nums[compStart];
                for (int j = compStart; j <= i; j++) compMax = Math.max(compMax, nums[j]);
                for (int j = compStart; j <= i; j++) ans[j] = compMax;
                compStart = i + 1;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int[] output) {
        if (output == null) return false;
        int[] expected = maxValue(nums);
        return Arrays.equals(expected, output);
    }
}