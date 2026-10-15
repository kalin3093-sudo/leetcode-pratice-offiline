import java.util.*;
class Marker {
    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(new int[]{edges[i][1], i});
            adj.get(edges[i][1]).add(new int[]{edges[i][0], i});
        }
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) diff[i] = (start.charAt(i) - '0') ^ (target.charAt(i) - '0');

        // ponytail: iterative DFS to avoid stack overflow on path trees (n up to 1e5)
        int[] parent = new int[n];
        int[] parentEdge = new int[n];
        Arrays.fill(parent, -2);
        parent[0] = -1;
        int[] order = new int[n];
        int sz = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            order[sz++] = u;
            for (int[] e : adj.get(u)) {
                if (e[0] == parent[u]) continue;
                parent[e[0]] = u;
                parentEdge[e[0]] = e[1];
                stack.push(e[0]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = sz - 1; i >= 1; i--) {
            int u = order[i];
            if (diff[u] == 1) {
                ans.add(parentEdge[u]);
                diff[u] ^= 1;
                diff[parent[u]] ^= 1;
            }
        }
        if (diff[0] == 1) return List.of(-1);
        Collections.sort(ans);
        return ans;
    }

    public boolean isCorrect(int n, int[][] edges, String start, String target, List<Integer> output) {
        List<Integer> expected = minimumFlips(n, edges, start, target);
        return expected.equals(output);
    }
}
