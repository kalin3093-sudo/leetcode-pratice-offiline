import java.util.*;

class Marker {
    public boolean canMakeEqual(int[] nums, int k) {
        return ops(nums, 1) <= k || ops(nums, -1) <= k;
    }

    private int ops(int[] nums, int target) {
        int[] a = nums.clone();
        int c = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != target) {
                a[i] = -a[i];
                a[i + 1] = -a[i + 1];
                c++;
            }
        }
        return a[a.length - 1] == target ? c : Integer.MAX_VALUE / 2;
    }

    public boolean isCorrect(int[] nums, int k, boolean output) {
        return output == canMakeEqual(nums, k);
    }
}
