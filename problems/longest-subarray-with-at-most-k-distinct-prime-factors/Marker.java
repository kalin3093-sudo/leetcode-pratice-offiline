import java.util.*;
class Marker {
    public int longestSubarray(int[] nums, int k) {
        int maxVal = 100000;
        int[] spf = new int[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= maxVal; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }
        
        List<Integer>[] factors = new List[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            int x = i;
            List<Integer> list = new ArrayList<>();
            while (x > 1) {
                int p = spf[x];
                list.add(p);
                while (x % p == 0) x /= p;
            }
            factors[i] = list;
        }
        
        int[] cnt = new int[maxVal + 1];
        int distinct = 0;
        int ans = 0;
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            for (int p : factors[nums[right]]) {
                if (cnt[p] == 0) distinct++;
                cnt[p]++;
            }
            
            while (distinct > k) {
                for (int p : factors[nums[left]]) {
                    cnt[p]--;
                    if (cnt[p] == 0) distinct--;
                }
                left++;
            }
            
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return longestSubarray(nums, k) == output;
    }
}