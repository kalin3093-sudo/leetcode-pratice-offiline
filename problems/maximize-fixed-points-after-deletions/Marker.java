import java.util.*;
class Marker {
    public int maxFixedPoints(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int len = 0;
        
        for (int i = 0; i < n; i++) {
            if (i >= nums[i]) {
                int val = i - nums[i];
                int pos = upperBound(dp, 0, len, val);
                dp[pos] = val;
                if (pos == len) len++;
            }
        }
        return len;
    }
    
    private int upperBound(int[] arr, int lo, int hi, int val) {
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (arr[mid] <= val) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public boolean isCorrect(int[] nums, int output) {
        return maxFixedPoints(nums) == output;
    }
}