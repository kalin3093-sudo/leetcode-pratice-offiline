import java.util.*;
class Marker {
    public int[] concatenatedDivisibility(int[] nums, int k) {
        int n = nums.length;
        int[] len = new int[n];
        int[] p10 = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = String.valueOf(nums[i]).length();
            long p = 1;
            for (int j = 0; j < len[i]; j++) p = p * 10 % k;
            p10[i] = (int) p;
        }
        int full = (1 << n) - 1;
        Boolean[][] memo = new Boolean[1 << n][k];
        if (!can(0, 0, nums, p10, k, full, memo)) return new int[0];
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));
        int[] res = new int[n];
        int mask = 0, rem = 0;
        for (int pos = 0; pos < n; pos++) {
            for (int i : order) {
                if ((mask >> i & 1) == 1) continue;
                int nr = (int) (((long) rem * p10[i] + nums[i]) % k);
                if (can(mask | 1 << i, nr, nums, p10, k, full, memo)) {
                    res[pos] = nums[i];
                    mask |= 1 << i;
                    rem = nr;
                    break;
                }
            }
        }
        return res;
    }

    private static boolean can(int mask, int rem, int[] nums, int[] p10, int k, int full, Boolean[][] memo) {
        if (mask == full) return rem == 0;
        if (memo[mask][rem] != null) return memo[mask][rem];
        for (int i = 0; i < nums.length; i++) {
            if ((mask >> i & 1) == 1) continue;
            int nr = (int) (((long) rem * p10[i] + nums[i]) % k);
            if (can(mask | 1 << i, nr, nums, p10, k, full, memo)) {
                return memo[mask][rem] = true;
            }
        }
        return memo[mask][rem] = false;
    }

    public boolean isCorrect(int[] nums, int k, int[] output) {
        if (output == null) return false;
        return Arrays.equals(concatenatedDivisibility(nums, k), output);
    }
}
