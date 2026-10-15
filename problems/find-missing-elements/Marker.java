import java.util.*;
class Marker {
    public List<Integer> findMissingElements(int[] nums) {
        int min = nums[0], max = nums[0];
        boolean[] present = new boolean[101];
        for (int x : nums) {
            min = Math.min(min, x);
            max = Math.max(max, x);
            present[x] = true;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (!present[i]) res.add(i);
        }
        return res;
    }

    public boolean isCorrect(int[] nums, List<Integer> output) {
        return findMissingElements(nums).equals(output);
    }
}