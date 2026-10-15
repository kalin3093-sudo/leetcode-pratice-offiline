import java.util.*;

class Marker {
    public int maxEqualAdjacentPairs(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.merge(x, 1, Integer::sum);
        int base = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) base++;
        }
        int ans = base;
        for (int x : freq.keySet()) {
            for (int y : freq.keySet()) {
                if (x == y) continue;
                int change = 0;
                for (int i = 0; i < n - 1; i++) {
                    int a = nums[i] == x ? y : nums[i];
                    int b = nums[i + 1] == x ? y : nums[i + 1];
                    if (a == b) {
                        if (nums[i] != nums[i + 1]) change++;
                    } else {
                        if (nums[i] == nums[i + 1]) change--;
                    }
                }
                ans = Math.max(ans, base + change);
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == maxEqualAdjacentPairs(nums);
    }
}