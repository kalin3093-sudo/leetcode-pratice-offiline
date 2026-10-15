import java.util.*;
class Marker {
    public int maxBalancedSubarray(int[] nums) {
        Map<Long, Integer> first = new HashMap<>();
        first.put(0L, -1);
        int pxor = 0, diff = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            pxor ^= nums[i];
            diff += (nums[i] & 1) == 0 ? 1 : -1;
            long key = ((long) pxor << 32) ^ (diff & 0xffffffffL);
            if (first.containsKey(key)) {
                ans = Math.max(ans, i - first.get(key));
            } else {
                first.put(key, i);
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return maxBalancedSubarray(nums) == output;
    }
}