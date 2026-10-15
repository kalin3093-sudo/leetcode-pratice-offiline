import java.util.*;

class Marker {
    public boolean partitionArray(int[] nums, int k) {
        int n = nums.length;
        if (n % k != 0) return false;
        int groups = n / k;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.merge(x, 1, Integer::sum);
        for (int f : freq.values())
            if (f > groups) return false;
        return true;
    }

    public boolean isCorrect(int[] nums, int k, boolean output) {
        return output == partitionArray(nums, k);
    }
}
