import java.util.*;

class Marker {
    public int minOperations(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int x : nums) {
            if (!seen.add(x)) {
                return (seen.size() + 2) / 3;
            }
        }
        return 0;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minOperations(nums) == output;
    }
}