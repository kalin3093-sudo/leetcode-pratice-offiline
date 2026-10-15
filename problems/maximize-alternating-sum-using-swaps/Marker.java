import java.util.*;
class Marker {
    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        int n = nums.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        
        for (int[] s : swaps) {
            union(parent, s[0], s[1]);
        }
        
        Map<Integer, List<Integer>> comps = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            comps.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }
        
        long ans = 0;
        for (List<Integer> indices : comps.values()) {
            List<Integer> vals = new ArrayList<>();
            for (int idx : indices) vals.add(nums[idx]);
            vals.sort((a, b) -> b - a);
            
            int evenCount = 0;
            for (int idx : indices) if (idx % 2 == 0) evenCount++;
            
            long sumAll = 0, sumTopE = 0;
            for (int v : vals) sumAll += v;
            for (int i = 0; i < evenCount; i++) sumTopE += vals.get(i);
            
            ans += 2 * sumTopE - sumAll;
        }
        return ans;
    }
    
    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }
    
    private void union(int[] parent, int a, int b) {
        int ra = find(parent, a), rb = find(parent, b);
        if (ra != rb) parent[rb] = ra;
    }

    public boolean isCorrect(int[] nums, int[][] swaps, long output) {
        return maxAlternatingSum(nums, swaps) == output;
    }
}