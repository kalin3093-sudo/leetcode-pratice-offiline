import java.util.*;

class Marker {
    private static final long MOD = 1000000007L;

    public int[] baseUnitConversions(int[][] conversions) {
        int n = conversions.length + 1;
        List<long[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] c : conversions) adj[c[0]].add(new long[] { c[1], c[2] });
        int[] res = new int[n];
        res[0] = 1;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (long[] e : adj[u]) {
                int v = (int) e[0];
                res[v] = (int) ((res[u] * e[1]) % MOD);
                q.add(v);
            }
        }
        return res;
    }

    public boolean isCorrect(int[][] conversions, int[] output) {
        return Arrays.equals(output, baseUnitConversions(conversions));
    }
}
