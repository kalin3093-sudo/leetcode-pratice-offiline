import java.util.*;

class Marker {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        TreeSet<Integer> separators = new TreeSet<>();
        separators.add(-1);
        separators.add(n);
        // Initial separators: zeros
        if (pos.containsKey(0)) {
            for (int idx : pos.get(0)) {
                separators.add(idx);
            }
        }
        
        int ops = 0;
        List<Integer> values = new ArrayList<>(pos.keySet());
        Collections.sort(values);
        
        for (int v : values) {
            if (v == 0) continue;
            List<Integer> indices = pos.get(v);
            Set<Integer> segs = new HashSet<>();
            for (int idx : indices) {
                Integer sep = separators.floor(idx);
                segs.add(sep);
            }
            ops += segs.size();
            for (int idx : indices) {
                separators.add(idx);
            }
        }
        return ops;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minOperations(nums) == output;
    }
}