import java.util.*;
class Marker {
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
        boolean[] present = new boolean[upper - lower + 1];
        for (int x : nums) {
            if (x >= lower && x <= upper) present[x - lower] = true;
        }
        List<List<Integer>> ans = new ArrayList<>();
        int i = 0;
        while (i < present.length) {
            if (!present[i]) {
                int start = i;
                while (i < present.length && !present[i]) i++;
                int end = i - 1;
                ans.add(Arrays.asList(lower + start, lower + end));
            } else {
                i++;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int lower, int upper, List<List<Integer>> output) {
        List<List<Integer>> expected = findDisappearedNumbers(nums, lower, upper);
        if (output == null || output.size() != expected.size()) return false;
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(output.get(i))) return false;
        }
        return true;
    }
}