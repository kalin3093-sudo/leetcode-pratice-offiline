import java.util.*;
class Marker {
    public long minMaxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long sumMax = solve(nums, k, true);
        long sumMin = solve(nums, k, false);
        return sumMax + sumMin;
    }
    
    private long solve(int[] nums, int k, boolean isMax) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        
        if (isMax) {
            for (int i = 0; i < n; i++) {
                while (!st.isEmpty() && nums[st.peek()] <= nums[i]) st.pop();
                left[i] = st.isEmpty() ? i + 1 : i - st.peek();
                st.push(i);
            }
            st.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop();
                right[i] = st.isEmpty() ? n - i : st.peek() - i;
                st.push(i);
            }
        } else {
            for (int i = 0; i < n; i++) {
                while (!st.isEmpty() && nums[st.peek()] >= nums[i]) st.pop();
                left[i] = st.isEmpty() ? i + 1 : i - st.peek();
                st.push(i);
            }
            st.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!st.isEmpty() && nums[st.peek()] > nums[i]) st.pop();
                right[i] = st.isEmpty() ? n - i : st.peek() - i;
                st.push(i);
            }
        }
        
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int L = left[i];
            int R = right[i];
            
            int minL = Math.max(1, L - k + 1);
            int maxL = L;
            if (minL > maxL) continue;
            
            for (int l = minL; l <= maxL; l++) {
                int t = l + k - L;
                int add = Math.min(R, t);
                if (add > 0) ans += (long) nums[i] * add;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        return minMaxSubarraySum(nums, k) == output;
    }
}