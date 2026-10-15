import java.util.*;
class Marker {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (map.containsKey(val)) {
                ans = Math.min(ans, i - map.get(val));
            }
            int rev = reverse(val);
            map.put(rev, i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int reverse(int x) {
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return rev;
    }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minMirrorPairDistance(nums);
        return expected == output;
    }
}