import java.util.*;

class Marker {
    public List<Integer> findValidElements(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == n - 1) {
                res.add(nums[i]);
                continue;
            }
            boolean left = true, right = true;
            for (int j = 0; j < i; j++) if (nums[j] >= nums[i]) left = false;
            for (int j = i + 1; j < n; j++) if (nums[j] >= nums[i]) right = false;
            if (left || right) res.add(nums[i]);
        }
        return res;
    }

    public boolean isCorrect(int[] nums, List<Integer> output) {
        return findValidElements(nums).equals(output);
    }
}
