import java.util.*;
class Marker {
    public String[] largestString(int[] nums) {
        int n = nums.length;
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            StringBuilder sb = new StringBuilder();
            for (int bit = 30; bit >= 0; bit--) {
                if ((x & (1 << bit)) != 0) {
                    sb.append((char) ('a' + bit));
                }
            }
            ans[i] = sb.toString();
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, String[] output) {
        if (output == null) return false;
        String[] expected = largestString(nums);
        return Arrays.equals(expected, output);
    }
}