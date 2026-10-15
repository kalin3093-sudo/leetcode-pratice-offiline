import java.util.*;
class Marker {
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int[] e : edges) children[e[0]].add(e[1]);
        return dfs(0, children, baseTime);
    }
    long dfs(int u, List<Integer>[] children, int[] baseTime) {
        if (children[u].isEmpty()) return baseTime[u];
        long earliest = Long.MAX_VALUE, latest = 0;
        for (int v : children[u]) {
            long ft = dfs(v, children, baseTime);
            earliest = Math.min(earliest, ft);
            latest = Math.max(latest, ft);
        }
        long ownDuration = (latest - earliest) + baseTime[u];
        return latest + ownDuration;
    }
    public boolean isCorrect(int n, int[][] edges, int[] baseTime, long output) {
        return finishTime(n, edges, baseTime) == output;
    }
}