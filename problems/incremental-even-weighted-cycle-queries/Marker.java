import java.util.*;
class Marker {
    public int numberOfEdgesAdded(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        int added = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (dsu.union(u, v, w)) added++;
        }
        return added;
    }

    public boolean isCorrect(int n, int[][] edges, int output) {
        return output == numberOfEdgesAdded(n, edges);
    }

    class DSU {
        int[] parent;
        int[] parity; // parity from node to its parent

        DSU(int n) {
            parent = new int[n];
            parity = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) {
                int px = parent[x];
                parent[x] = find(parent[x]);
                parity[x] ^= parity[px];
            }
            return parent[x];
        }

        // returns true if edge can be added (no odd-weight cycle formed)
        boolean union(int u, int v, int w) {
            int ru = find(u), rv = find(v);
            int pu = parity[u], pv = parity[v];
            if (ru == rv) {
                // cycle formed: parity(u->v) = pu ^ pv, cycle weight = (pu ^ pv) ^ w
                // need even cycle => (pu ^ pv) ^ w == 0 => pu ^ pv == w
                return (pu ^ pv) == w;
            }
            // merge: set parent[ru] = rv, need parity[ru] such that pu ^ parity[ru] ^ pv == w
            // => parity[ru] = pu ^ pv ^ w
            parent[ru] = rv;
            parity[ru] = pu ^ pv ^ w;
            return true;
        }
    }
}