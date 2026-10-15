import java.util.*;

public class Marker {
    static int rev(int v) {
        int r = 0;
        while (v > 0) {
            r = r * 10 + v % 10;
            v /= 10;
        }
        return r;
    }

    public int countDistinctIntegers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int v : nums) {
            set.add(v);
            set.add(rev(v));
        }
        return set.size();
    }

    public boolean isCorrect(int[] nums, int out) {
        return countDistinctIntegers(nums) == out;
    }
}
