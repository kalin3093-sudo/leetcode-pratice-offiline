import java.util.*;
class Marker {
    public int minCost(int n, int[][] edges, int k) {
        if (k >= n) return 0;
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int comps = n;
        for (int[] e : edges) {
            int a = find(parent, e[0]);
            int b = find(parent, e[1]);
            if (a == b) continue;
            parent[a] = b;
            comps--;
            if (comps <= k) return e[2];
        }
        return 0;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public boolean isCorrect(int n, int[][] edges, int k, int output) {
        return minCost(n, edges, k) == output;
    }
}
