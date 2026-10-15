import java.util.*;
class Marker {
    public int maxSubarray(int[] nums) {
        int n = nums.length;
        int ans = 1;
        
        for (int l = 0; l < n; l++) {
            int[] cnt = new int[501];
            for (int r = l; r < n; r++) {
                cnt[nums[r]]++;
                boolean valid = true;
                
                for (int a = 1; a <= 500 && valid; a++) {
                    if (cnt[a] == 0) continue;
                    for (int b = a; b <= 500 && valid; b++) {
                        if (cnt[b] == 0) continue;
                        if (a == b && cnt[a] < 2) continue;
                        
                        int sum = a + b;
                        if (sum > 500) continue;
                        if (cnt[sum] > 0) {
                            if (sum != a && sum != b) {
                                valid = false;
                            } else if (sum == a && sum == b) {
                                if (cnt[sum] < 3) valid = false;
                            } else {
                                if (cnt[sum] < 2) valid = false;
                            }
                        }
                    }
                }
                
                if (valid) {
                    ans = Math.max(ans, r - l + 1);
                }
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return maxSubarray(nums) == output;
    }
}