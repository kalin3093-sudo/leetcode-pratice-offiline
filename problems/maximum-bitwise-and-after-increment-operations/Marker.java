import java.util.*;

class Marker {
    public int maximumAND(int[] nums, int k, int m) {
        int res = 0;
        for (int bit = 30; bit >= 0; bit--) {
            int candidate = res | (1 << bit);
            int[] costs = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                costs[i] = getCost(nums[i], candidate);
            }
            Arrays.sort(costs);
            long sum = 0;
            for (int i = 0; i < m; i++) {
                sum += costs[i];
            }
            if (sum <= k) {
                res = candidate;
            }
        }
        return res;
    }

    private int getCost(int num, int mask) {
        if ((num & mask) == mask) return 0;
        int val = num;
        for (int b = 30; b >= 0; b--) {
            if ((mask & (1 << b)) != 0 && (val & (1 << b)) == 0) {
                int add = (1 << b) - (val & ((1 << b) - 1));
                val += add;
            }
        }
        return val - num;
    }

    public boolean isCorrect(int[] nums, int k, int m, int output) {
        return output == maximumAND(nums, k, m);
    }
}