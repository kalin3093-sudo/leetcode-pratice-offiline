import java.util.*;

class Marker {
    public int countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length;
        int ans = 0;
        for (int l = 0; l < n; l++) {
            int x = 0, y = 0;
            for (int r = l; r < n; r++) {
                if (nums[r] % 2 == 0) x++;
                else y++;
                if (y > 0 && 1L * b * x <= 1L * a * y) ans++;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int a, int b, int output) {
        return output == countRatioSubarrays(nums, a, b);
    }
}