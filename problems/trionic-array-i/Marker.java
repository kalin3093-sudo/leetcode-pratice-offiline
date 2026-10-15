import java.util.*;

class Marker {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        for (int p = 1; p < n - 1; p++) {
            for (int q = p + 1; q < n - 1; q++) {
                if (check(nums, 0, p, 1) && check(nums, p, q, -1) && check(nums, q, n - 1, 1))
                    return true;
            }
        }
        return false;
    }

    private boolean check(int[] a, int l, int r, int dir) {
        for (int i = l; i < r; i++) {
            if (dir == 1 && a[i] >= a[i + 1]) return false;
            if (dir == -1 && a[i] <= a[i + 1]) return false;
        }
        return true;
    }

    public boolean isCorrect(int[] nums, boolean output) {
        return output == isTrionic(nums);
    }
}
