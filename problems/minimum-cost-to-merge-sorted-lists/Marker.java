import java.util.*;
class Marker {
    public long minMergeCost(int[][] lists) {
        int n = lists.length;
        int[] lens = new int[n];
        for (int i = 0; i < n; i++) lens[i] = lists[i].length;

        // Collect all values for binary search
        List<Integer> allVals = new ArrayList<>();
        for (int[] list : lists) {
            for (int x : list) allVals.add(x);
        }
        Collections.sort(allVals);
        // Deduplicate
        int[] vals = allVals.stream().distinct().mapToInt(Integer::intValue).toArray();

        int totalMasks = 1 << n;
        int[] lenMask = new int[totalMasks];
        int[] medianMask = new int[totalMasks];

        // Precompute length for each mask
        for (int mask = 1; mask < totalMasks; mask++) {
            int lsb = mask & -mask;
            int i = Integer.numberOfTrailingZeros(lsb);
            lenMask[mask] = lenMask[mask ^ lsb] + lens[i];
        }

        // Precompute median for each mask using binary search on vals
        for (int mask = 1; mask < totalMasks; mask++) {
            int len = lenMask[mask];
            int k = (len - 1) / 2; // 0-indexed, left median
            int lo = 0, hi = vals.length - 1;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (countLE(mask, lists, vals[mid]) > k) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            medianMask[mask] = vals[lo];
        }

        // DP over masks
        long[] dp = new long[totalMasks];
        Arrays.fill(dp, Long.MAX_VALUE);
        for (int i = 0; i < n; i++) dp[1 << i] = 0;

        for (int mask = 1; mask < totalMasks; mask++) {
            if (Integer.bitCount(mask) == 1) continue;
            // Iterate proper non-empty submasks
            for (int s = (mask - 1) & mask; s > 0; s = (s - 1) & mask) {
                int t = mask ^ s;
                if (t == 0) continue;
                long cost = dp[s] + dp[t] + lenMask[s] + lenMask[t] + Math.abs((long)medianMask[s] - medianMask[t]);
                if (cost < dp[mask]) dp[mask] = cost;
            }
        }
        return dp[totalMasks - 1];
    }

    private int countLE(int mask, int[][] lists, int x) {
        int cnt = 0;
        for (int i = 0; i < lists.length; i++) {
            if ((mask & (1 << i)) != 0) {
                int[] list = lists[i];
                // upper bound: first index > x
                int l = 0, r = list.length;
                while (l < r) {
                    int m = (l + r) >>> 1;
                    if (list[m] <= x) l = m + 1;
                    else r = m;
                }
                cnt += l;
            }
        }
        return cnt;
    }

    public boolean isCorrect(int[][] lists, long output) {
        long expected = minMergeCost(lists);
        return expected == output;
    }
}