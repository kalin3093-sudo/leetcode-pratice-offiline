import java.util.*;
class Marker {
    private static final int MOD = 1_000_000_007;
    public int divisibleGame(int[] nums) {
        int maxV = 0;
        for (int x : nums) maxV = Math.max(maxV, x);
        Set<Integer> candidates = new HashSet<>();
        candidates.add(2);
        for (int x : nums) {
            for (int d = 2; d * d <= x; d++) {
                if (x % d == 0) {
                    candidates.add(d);
                    if (d != x / d) candidates.add(x / d);
                }
            }
            if (x > 1) candidates.add(x);
        }
        long bestDiff = Long.MIN_VALUE;
        int bestK = Integer.MAX_VALUE;
        for (int k : candidates) {
            int[] a = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                a[i] = nums[i] % k == 0 ? nums[i] : -nums[i];
            }
            long cur = a[0], maxSub = a[0];
            for (int i = 1; i < a.length; i++) {
                cur = Math.max(a[i], cur + a[i]);
                maxSub = Math.max(maxSub, cur);
            }
            if (maxSub > bestDiff || (maxSub == bestDiff && k < bestK)) {
                bestDiff = maxSub;
                bestK = k;
            }
        }
        long ans = (bestDiff % MOD * bestK) % MOD;
        return (int) ((ans + MOD) % MOD);
    }

    public boolean isCorrect(int[] nums, int output) {
        return divisibleGame(nums) == output;
    }
}