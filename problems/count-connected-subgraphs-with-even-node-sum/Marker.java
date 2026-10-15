import java.util.*;

public class Marker {

    public int evenSumSubgraphs(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int count = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            int sum = 0;
            for (int i = 0; i < n; i++) if (((mask >> i) & 1) == 1) sum += nums[i];
            if ((sum & 1) == 1) continue;
            int start = Integer.numberOfTrailingZeros(mask);
            int seen = 0;
            Deque<Integer> q = new ArrayDeque<>();
            q.add(start);
            seen |= 1 << start;
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v : adj[u]) {
                    if (((mask >> v) & 1) == 1 && ((seen >> v) & 1) == 0) {
                        seen |= 1 << v;
                        q.add(v);
                    }
                }
            }
            if (seen == mask) count++;
        }
        return count;
    }

    public boolean isCorrect(int[] nums, int[][] edges, int out) {
        return evenSumSubgraphs(nums, edges) == out;
    }
}
