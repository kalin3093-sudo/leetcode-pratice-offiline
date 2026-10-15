import java.util.*;
class Marker {
    public int[] sortByReflection(int[] nums) {
        Integer[] a = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) a[i] = nums[i];
        Arrays.sort(a, (x, y) -> {
            int rx = reflect(x), ry = reflect(y);
            if (rx != ry) return rx - ry;
            return x - y;
        });
        int[] res = new int[nums.length];
        for (int i = 0; i < a.length; i++) res[i] = a[i];
        return res;
    }

    private int reflect(int x) {
        int r = 0;
        while (x > 0) { r = (r << 1) | (x & 1); x >>= 1; }
        return r;
    }

    public boolean isCorrect(int[] nums, int[] output) {
        return Arrays.equals(sortByReflection(nums), output);
    }
}
