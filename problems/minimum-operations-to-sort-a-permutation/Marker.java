import java.util.*;
class Marker {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int p = 0;
        for (int i = 0; i < n; i++) if (nums[i] == 0) p = i;
        int ans = Integer.MAX_VALUE;
        boolean ok = true;
        for (int j = 0; j < n; j++) if (nums[(p + j) % n] != j) ok = false;
        if (ok) ans = Math.min(ans, p);
        int p2 = n - 1 - p;
        boolean ok2 = true;
        for (int j = 0; j < n; j++) if (nums[n - 1 - ((p2 + j) % n)] != j) ok2 = false;
        if (ok2) ans = Math.min(ans, 1 + Math.min(p2, n - p2));
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == minOperations(nums);
    }
}
