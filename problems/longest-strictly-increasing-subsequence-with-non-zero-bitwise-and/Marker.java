import java.util.*;
class Marker {
    public int longestSubsequence(int[] nums) {
        int ans = 0;
        for (int b = 0; b < 30; b++) {
            int[] filtered = new int[nums.length];
            int m = 0;
            for (int x : nums) {
                if ((x >> b & 1) == 1) filtered[m++] = x;
            }
            if (m == 0) continue;
            int[] dp = new int[m];
            int[] tails = new int[m];
            int len = 0;
            for (int i = 0; i < m; i++) {
                int pos = Arrays.binarySearch(tails, 0, len, filtered[i]);
                if (pos < 0) pos = ~pos;
                tails[pos] = filtered[i];
                if (pos == len) len++;
            }
            ans = Math.max(ans, len);
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return longestSubsequence(nums) == output;
    }
}