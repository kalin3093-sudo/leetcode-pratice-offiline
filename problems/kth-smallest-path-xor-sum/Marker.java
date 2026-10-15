import java.util.*;
class Marker {
    public int[] kthSmallest(int[] par, int[] vals, int[][] queries) {
        int n = vals.length;
        List<Integer>[] tree = new List[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) tree[par[i]].add(i);

        List<int[]>[] qs = new List[n];
        for (int i = 0; i < n; i++) qs[i] = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            qs[queries[i][0]].add(new int[]{queries[i][1], i});
        }

        int[] pathXor = new int[n];
        int[] ans = new int[queries.length];
        dfs(0, -1, tree, vals, pathXor, qs, ans);
        return ans;
    }

    private TreeSet<Integer> dfs(int u, int p, List<Integer>[] tree, int[] vals, int[] pathXor, List<int[]>[] qs, int[] ans) {
        pathXor[u] = (p == -1 ? 0 : pathXor[p]) ^ vals[u];
        TreeSet<Integer> set = new TreeSet<>();
        set.add(pathXor[u]);
        for (int v : tree[u]) {
            TreeSet<Integer> childSet = dfs(v, u, tree, vals, pathXor, qs, ans);
            if (childSet.size() > set.size()) {
                TreeSet<Integer> tmp = set;
                set = childSet;
                childSet = tmp;
            }
            set.addAll(childSet);
        }
        for (int[] q : qs[u]) {
            int k = q[0], idx = q[1];
            if (k > set.size()) ans[idx] = -1;
            else {
                int i = 0;
                for (int x : set) {
                    if (i++ == k - 1) { ans[idx] = x; break; }
                }
            }
        }
        return set;
    }

    public boolean isCorrect(int[] par, int[] vals, int[][] queries, int[] output) {
        if (output == null) return false;
        int[] expected = kthSmallest(par, vals, queries);
        return Arrays.equals(expected, output);
    }
}