import java.util.*;

public class Marker {

    public int[] countOppositeParity(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int odd = 0, even = 0;
        for (int v : nums) {
            if ((v & 1) == 1) odd++; else even++;
        }
        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 1) {
                ans[i] = even;
                odd--;
            } else {
                ans[i] = odd;
                even--;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int[] out) {
        return Arrays.equals(countOppositeParity(nums), out);
    }
}
