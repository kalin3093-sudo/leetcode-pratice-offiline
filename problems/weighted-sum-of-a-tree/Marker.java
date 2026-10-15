import java.util.*;

class Marker {
    public long weightedSum(int[] parent, int[] nums) {
        int n = parent.length;
        int[] depth = new int[n];
        depth[0] = 1;
        for (int i = 1; i < n; i++) {
            int d = 0, cur = i;
            while (cur != 0 && depth[cur] == 0) {
                d++;
                cur = parent[cur];
            }
            d += depth[cur];
            cur = i;
            while (depth[cur] == 0) {
                depth[cur] = d;
                d--;
                cur = parent[cur];
            }
        }
        int h = 0;
        for (int i = 0; i < n; i++) h = Math.max(h, depth[i]);
        long sum = 0;
        for (int i = 0; i < n; i++) sum += (long) nums[i] * (h - depth[i] + 1);
        return sum;
    }

    public boolean isCorrect(int[] parent, int[] nums, long output) {
        return output == weightedSum(parent, nums);
    }
}
