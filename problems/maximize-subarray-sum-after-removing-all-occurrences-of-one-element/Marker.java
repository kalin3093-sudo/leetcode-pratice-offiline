import java.util.*;
class Marker {
    public long maxSubarraySum(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        long ans = kadane(nums);
        
        for (Map.Entry<Integer, List<Integer>> e : pos.entrySet()) {
            int x = e.getKey();
            if (x >= 0) continue;
            
            int[] modified = new int[n - e.getValue().size()];
            int idx = 0;
            for (int v : nums) {
                if (v != x) modified[idx++] = v;
            }
            if (modified.length > 0) {
                ans = Math.max(ans, kadane(modified));
            }
        }
        return ans;
    }
    
    private long kadane(int[] nums) {
        long maxSum = nums[0];
        long curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            maxSum = Math.max(maxSum, curr);
        }
        return maxSum;
    }

    public boolean isCorrect(int[] nums, long output) {
        return maxSubarraySum(nums) == output;
    }
}