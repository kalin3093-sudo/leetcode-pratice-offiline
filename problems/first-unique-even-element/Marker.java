import java.util.*;
class Marker {
    public int firstUniqueEven(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.merge(x, 1, Integer::sum);
        for (int x : nums) {
            if ((x & 1) == 0 && freq.get(x) == 1) return x;
        }
        return -1;
    }
    public boolean isCorrect(int[] nums, int output) {
        return firstUniqueEven(nums) == output;
    }
}