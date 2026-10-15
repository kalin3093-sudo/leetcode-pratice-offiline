import java.util.*;

class Marker {
    public int maxActivated(int[][] points) {
        int n = points.length;
        Map<Integer, List<Integer>> xMap = new HashMap<>();
        Map<Integer, List<Integer>> yMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            xMap.computeIfAbsent(points[i][0], k -> new ArrayList<>()).add(i);
            yMap.computeIfAbsent(points[i][1], k -> new ArrayList<>()).add(i);
        }
        
        DSU dsu = new DSU(n);
        for (List<Integer> list : xMap.values()) {
            for (int i = 1; i < list.size(); i++) {
                dsu.union(list.get(0), list.get(i));
            }
        }
        for (List<Integer> list : yMap.values()) {
            for (int i = 1; i < list.size(); i++) {
                dsu.union(list.get(0), list.get(i));
            }
        }
        
        Map<Integer, Integer> compSize = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            compSize.put(root, compSize.getOrDefault(root, 0) + 1);
        }
        
        if (compSize.size() == 1) {
            return n + 1;
        }
        
        List<Integer> sizes = new ArrayList<>(compSize.values());
        Collections.sort(sizes, Collections.reverseOrder());
        return sizes.get(0) + sizes.get(1) + 1;
    }
    
    static class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) parent[rx] = ry;
            else if (rank[rx] > rank[ry]) parent[ry] = rx;
            else { parent[ry] = rx; rank[rx]++; }
        }
    }

    public boolean isCorrect(int[][] points, int output) {
        return output == maxActivated(points);
    }
}