import java.util.*;
class Marker {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        int ans = Integer.MAX_VALUE;
        for (List<Integer> list : pos.values()) {
            for (int i = 2; i < list.size(); i++) {
                int dist = 2 * (list.get(i) - list.get(i - 2));
                if (dist < ans) ans = dist;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minimumDistance(nums);
        return expected == output;
    }
}
