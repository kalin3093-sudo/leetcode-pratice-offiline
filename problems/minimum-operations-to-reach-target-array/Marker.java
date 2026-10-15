import java.util.*;
class Marker {
    public int minOperations(int[] nums, int[] target) {
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
            if (nums[i] != target[i]) seen.add(nums[i]);
        return seen.size();
    }

    public boolean isCorrect(int[] nums, int[] target, int output) {
        return output == minOperations(nums, target);
    }
}
