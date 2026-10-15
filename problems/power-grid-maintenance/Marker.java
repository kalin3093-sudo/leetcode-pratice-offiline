import java.util.*;
class Marker {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        int[] parent = new int[c + 1];
        for (int i = 1; i <= c; i++) parent[i] = i;
        for (int[] e : connections) {
            int a = find(parent, e[0]), b = find(parent, e[1]);
            if (a != b) parent[a] = b;
        }
        Map<Integer, TreeSet<Integer>> sets = new HashMap<>();
        for (int i = 1; i <= c; i++) sets.computeIfAbsent(find(parent, i), k -> new TreeSet<>()).add(i);
        boolean[] online = new boolean[c + 1];
        Arrays.fill(online, true);
        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 2) {
                online[q[1]] = false;
                sets.get(find(parent, q[1])).remove(q[1]);
            } else {
                if (online[q[1]]) ans.add(q[1]);
                else {
                    TreeSet<Integer> ts = sets.get(find(parent, q[1]));
                    ans.add(ts == null || ts.isEmpty() ? -1 : ts.first());
                }
            }
        }
        int[] r = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) r[i] = ans.get(i);
        return r;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) { parent[x] = parent[parent[x]]; x = parent[x]; }
        return x;
    }

    public boolean isCorrect(int c, int[][] connections, int[][] queries, int[] output) {
        return Arrays.equals(processQueries(c, connections, queries), output);
    }
}
