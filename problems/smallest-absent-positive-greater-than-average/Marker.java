import java.util.*;

class Marker {
    public int smallestAbsent(int[] nums) {
        int sum = 0;
        for (int v : nums)
            sum += v;
        Set<Integer> set = new HashSet<>();
        for (int v : nums)
            set.add(v);
        int x = (int) Math.floor((double) sum / nums.length) + 1;
        if (x < 1)
            x = 1;
        while (set.contains(x))
            x++;
        return x;
    }

    public boolean isCorrect(int[] nums, int output) {
        return smallestAbsent(nums) == output;
    }
}
