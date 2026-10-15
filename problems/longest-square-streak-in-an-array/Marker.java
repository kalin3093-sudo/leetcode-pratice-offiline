import java.util.*;
class Marker {
    public int longestSquareStreak(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        
        int ans = 0;
        for (int x : nums) {
            int len = 0;
            long cur = x;
            while (set.contains((int) cur)) {
                len++;
                if (cur > 100000) break;
                cur = cur * cur;
            }
            if (len >= 2) ans = Math.max(ans, len);
        }
        return ans == 0 ? -1 : ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return longestSquareStreak(nums) == output;
    }
}