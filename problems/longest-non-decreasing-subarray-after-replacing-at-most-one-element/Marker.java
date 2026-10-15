import java.util.*;
class Marker {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;
        
        int[] pref = new int[n];
        int[] suff = new int[n];
        
        pref[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] >= nums[i - 1]) pref[i] = pref[i - 1] + 1;
            else pref[i] = 1;
        }
        
        suff[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) suff[i] = suff[i + 1] + 1;
            else suff[i] = 1;
        }
        
        int ans = 1;
        for (int v : pref) ans = Math.max(ans, v);
        for (int v : suff) ans = Math.max(ans, v);
        
        for (int i = 1; i < n - 1; i++) {
            if (nums[i + 1] >= nums[i - 1]) {
                ans = Math.max(ans, pref[i - 1] + suff[i + 1] + 1);
            } else {
                ans = Math.max(ans, Math.max(pref[i - 1], suff[i + 1]) + 1);
            }
        }
        
        ans = Math.max(ans, pref[n - 2] + 1);
        ans = Math.max(ans, suff[1] + 1);
        
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return longestSubarray(nums) == output;
    }
}