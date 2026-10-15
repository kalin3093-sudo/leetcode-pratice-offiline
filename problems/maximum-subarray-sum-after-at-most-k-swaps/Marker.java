import java.util.*;
class Marker {
    public long maxSum(int[] nums, int k) {
        int n = nums.length;
        long ans = Long.MIN_VALUE;
        
        for (int l = 0; l < n; l++) {
            List<Integer> inside = new ArrayList<>();
            List<Integer> outside = new ArrayList<>();
            for (int i = 0; i < n; i++) outside.add(nums[i]);
            
            long insideSum = 0;
            for (int r = l; r < n; r++) {
                inside.add(nums[r]);
                insideSum += nums[r];
                outside.remove(Integer.valueOf(nums[r]));
                
                Collections.sort(inside);
                Collections.sort(outside, Collections.reverseOrder());
                
                long curSum = insideSum;
                int swaps = 0;
                for (int i = 0; i < Math.min(k, Math.min(inside.size(), outside.size())); i++) {
                    if (outside.get(i) > inside.get(i)) {
                        curSum += outside.get(i) - inside.get(i);
                        swaps++;
                    } else {
                        break;
                    }
                }
                ans = Math.max(ans, curSum);
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        long expected = maxSum(nums, k);
        return expected == output;
    }
}