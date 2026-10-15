import java.util.*;
class Marker {
    public int minIncrease(int n, int[][] edges, int[] cost) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        // ponytail: iterative DFS to avoid stack overflow on path trees (n up to 1e5)
        int[] parent = new int[n];
        Arrays.fill(parent, -2);
        parent[0] = -1;
        int[] order = new int[n];
        int sz = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            order[sz++] = u;
            for (int v : adj.get(u)) {
                if (v == parent[u]) continue;
                parent[v] = u;
                stack.push(v);
            }
        }
        long[] down = new long[n]; // max path sum from u down to a leaf in its subtree
        for (int i = sz - 1; i >= 0; i--) {
            int u = order[i];
            for (int v : adj.get(u)) {
                if (v == parent[u]) continue;
                down[u] = Math.max(down[u], down[v]);
            }
            down[u] += cost[u];
        }
        // full[u] = max root-to-leaf path sum in subtree(u) = ps[parent[u]] + down[u]
        int ans = 0;
        long[] ps = new long[n]; // root-to-u path sum
        ps[0] = cost[0];
        for (int i = 1; i < sz; i++) {
            int u = order[i];
            ps[u] = ps[parent[u]] + cost[u];
            long fullU = ps[parent[u]] + down[u];
            long fullP = (parent[u] == 0 ? 0 : ps[parent[parent[u]]]) + down[parent[u]];
            if (fullU < fullP) ans++;
        }
        return ans;
    }

    public boolean isCorrect(int n, int[][] edges, int[] cost, int output) {
        int expected = minIncrease(n, edges, cost);
        return expected == output;
    }
}
