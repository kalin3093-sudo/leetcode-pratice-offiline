import java.util.*;
class Marker {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }
        
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            long v = 2L * nums[i];
            vals[i] = (int) ((v % k + k) % k);
        }
        
        int low = 1, high = n, ans = 0;
        int[] freq = new int[k];
        
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (check(nums, pref, vals, k, mid, freq)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    
    private boolean check(int[] nums, long[] pref, int[] vals, int k, int len, int[] freq) {
        int n = nums.length;
        Arrays.fill(freq, 0);
        
        long sum = pref[len] - pref[0];
        int rem = (int) ((sum % k + k) % k);
        for (int i = 0; i < len; i++) {
            freq[vals[i]]++;
        }
        if (rem == 0 || freq[rem] > 0) return true;
        
        for (int l = 1; l + len - 1 < n; l++) {
            int r = l + len - 1;
            sum = pref[r + 1] - pref[l];
            rem = (int) ((sum % k + k) % k);
            
            freq[vals[l - 1]]--;
            freq[vals[r]]++;
            
            if (rem == 0 || freq[rem] > 0) return true;
        }
        return false;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return longestSubarray(nums, k) == output;
    }
}