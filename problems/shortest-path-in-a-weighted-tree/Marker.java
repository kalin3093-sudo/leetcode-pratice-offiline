import java.util.*;

class Marker {
    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        List<int[]>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(new int[] { e[1], e[2] });
            adj[e[1]].add(new int[] { e[0], e[2] });
        }
        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                for (int[] e : adj[q[1]])
                    if (e[0] == q[2])
                        e[1] = q[3];
                for (int[] e : adj[q[2]])
                    if (e[0] == q[1])
                        e[1] = q[3];
            } else {
                int target = q[1];
                long[] dist = new long[n + 1];
                Arrays.fill(dist, -1);
                Deque<Integer> st = new ArrayDeque<>();
                st.push(1);
                dist[1] = 0;
                while (!st.isEmpty()) {
                    int u = st.pop();
                    for (int[] e : adj[u]) {
                        if (dist[e[0]] < 0) {
                            dist[e[0]] = dist[u] + e[1];
                            st.push(e[0]);
                        }
                    }
                }
                ans.add((int) dist[target]);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = ans.get(i);
        return res;
    }

    public boolean isCorrect(int n, int[][] edges, int[][] queries, int[] output) {
        int[] expected = treeQueries(n, edges, queries);
        if (output == null || output.length != expected.length)
            return false;
        for (int i = 0; i < expected.length; i++)
            if (expected[i] != output[i])
                return false;
        return true;
    }
}
