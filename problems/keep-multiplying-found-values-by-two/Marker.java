import java.util.*;
class Marker {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int cur = original;
        while (set.contains(cur)) cur *= 2;
        return cur;
    }

    public boolean isCorrect(int[] nums, int original, int output) {
        return output == findFinalValue(nums, original);
    }
}