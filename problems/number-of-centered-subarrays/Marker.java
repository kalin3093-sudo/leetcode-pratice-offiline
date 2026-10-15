import java.util.*;

class Marker {
    public int centeredSubarrays(int[] nums) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            HashSet<Integer> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                sum += nums[j];
                seen.add(nums[j]);
                if (seen.contains(sum)) ans++;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == centeredSubarrays(nums);
    }
}
