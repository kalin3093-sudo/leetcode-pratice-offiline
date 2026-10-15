import java.util.*;
class Marker {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        
        // Doubly linked list structure
        int[] left = new int[n];
        int[] right = new int[n];
        long[] vals = new long[n];
        for (int i = 0; i < n; i++) {
            left[i] = i - 1;
            right[i] = i + 1;
            vals[i] = nums[i];
        }
        right[n - 1] = -1;
        
        // Pair sums with min-heap (sum, left_index)
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });
        
        boolean[] removed = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            pq.offer(new long[]{vals[i] + vals[i + 1], i});
        }
        
        int ops = 0;
        while (!pq.isEmpty()) {
            // Check if current array is non-decreasing
            if (isNonDecreasing(vals, removed, left, right)) {
                return ops;
            }
            
            long[] top = pq.poll();
            long sum = top[0];
            int i = (int) top[1];
            
            if (removed[i] || right[i] == -1 || removed[right[i]]) continue;
            
            // Check if this pair is still the leftmost minimum
            int j = right[i];
            if (vals[i] + vals[j] != sum) continue;
            
            // Merge i and j
            ops++;
            vals[i] = sum;
            removed[j] = true;
            right[i] = right[j];
            if (right[j] != -1) left[right[j]] = i;
            
            // Add new pair with right neighbor
            if (right[i] != -1) {
                pq.offer(new long[]{vals[i] + vals[right[i]], i});
            }
            
            // Add new pair with left neighbor
            if (left[i] != -1) {
                pq.offer(new long[]{vals[left[i]] + vals[i], left[i]});
            }
        }
        
        return ops;
    }
    
    private boolean isNonDecreasing(long[] vals, boolean[] removed, int[] left, int[] right) {
        int prevIdx = -1;
        for (int i = 0; i < vals.length; i++) {
            if (!removed[i]) {
                if (prevIdx != -1 && vals[prevIdx] > vals[i]) {
                    return false;
                }
                prevIdx = i;
            }
        }
        return true;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minimumPairRemoval(nums) == output;
    }
}