import java.util.*;

class Marker {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int v : nums)
            set.add(v);
        for (int m = k; ; m += k)
            if (!set.contains(m))
                return m;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return missingMultiple(nums, k) == output;
    }
}
