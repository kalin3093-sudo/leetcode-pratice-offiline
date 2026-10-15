import java.util.*;
class Marker {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        int LOG = 17; // 2^16 = 65536 > 50000
        int[][] stMax = new int[LOG][n];
        int[][] stMin = new int[LOG][n];
        
        for (int i = 0; i < n; i++) {
            stMax[0][i] = nums[i];
            stMin[0][i] = nums[i];
        }
        
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                stMax[j][i] = Math.max(stMax[j - 1][i], stMax[j - 1][i + (1 << (j - 1))]);
                stMin[j][i] = Math.min(stMin[j - 1][i], stMin[j - 1][i + (1 << (j - 1))]);
            }
        }
        
        int[] log = new int[n + 1];
        for (int i = 2; i <= n; i++) log[i] = log[i >> 1] + 1;
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));
        // [value, l, r]
        for (int l = 0; l < n; l++) {
            int r = n - 1;
            int j = log[r - l + 1];
            int mx = Math.max(stMax[j][l], stMax[j][r - (1 << j) + 1]);
            int mn = Math.min(stMin[j][l], stMin[j][r - (1 << j) + 1]);
            pq.offer(new long[]{(long)mx - mn, l, r});
        }
        
        long total = 0;
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            long[] top = pq.poll();
            total += top[0];
            int l = (int) top[1];
            int r = (int) top[2];
            if (r > l) {
                int newR = r - 1;
                int j = log[newR - l + 1];
                int mx = Math.max(stMax[j][l], stMax[j][newR - (1 << j) + 1]);
                int mn = Math.min(stMin[j][l], stMin[j][newR - (1 << j) + 1]);
                pq.offer(new long[]{(long)mx - mn, l, newR});
            }
        }
        return total;
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        long expected = maxTotalValue(nums, k);
        return expected == output;
    }
}