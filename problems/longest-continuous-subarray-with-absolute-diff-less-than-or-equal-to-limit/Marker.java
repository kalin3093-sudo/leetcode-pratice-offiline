import java.util.*;
class Marker {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDq = new ArrayDeque<>(); // decreasing
        Deque<Integer> minDq = new ArrayDeque<>(); // increasing
        int left = 0, ans = 0;
        for (int right = 0; right < nums.length; right++) {
            int x = nums[right];
            while (!maxDq.isEmpty() && maxDq.peekLast() < x) maxDq.pollLast();
            maxDq.offerLast(x);
            while (!minDq.isEmpty() && minDq.peekLast() > x) minDq.pollLast();
            minDq.offerLast(x);
            
            while (maxDq.peekFirst() - minDq.peekFirst() > limit) {
                if (maxDq.peekFirst() == nums[left]) maxDq.pollFirst();
                if (minDq.peekFirst() == nums[left]) minDq.pollFirst();
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int limit, int output) {
        return output == longestSubarray(nums, limit);
    }
}