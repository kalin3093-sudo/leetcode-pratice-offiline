import java.util.*;

class Marker {
    public int[] rotateElements(int[] nums, int k) {
        List<Integer> vals = new ArrayList<>();
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                vals.add(nums[i]);
                idx.add(i);
            }
        }
        int m = vals.size();
        if (m > 0) {
            int shift = k % m;
            for (int i = 0; i < m; i++)
                nums[idx.get(i)] = vals.get((i + shift) % m);
        }
        return nums;
    }

    public boolean isCorrect(int[] nums, int k, int[] output) {
        int[] expected = rotateElements(nums.clone(), k);
        if (output == null || output.length != expected.length)
            return false;
        for (int i = 0; i < expected.length; i++)
            if (expected[i] != output[i])
                return false;
        return true;
    }
}
