import java.util.*;
class Marker {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + nums[i];
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long sum = pref[j + 1] - pref[i];
                if (sum % k == 0) {
                    ans = Math.max(ans, j - i + 1);
                } else {
                    long need = sum % k;
                    for (int p = i; p <= j; p++) {
                        long newSum = sum - 2L * nums[p];
                        if (newSum % k == 0) {
                            ans = Math.max(ans, j - i + 1);
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return longestSubarray(nums, k) == output;
    }
}