import java.util.*;

class Marker {
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        Deque<Integer> maxDq = new ArrayDeque<>();
        Deque<Integer> minDq = new ArrayDeque<>();
        long ans = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            while (!maxDq.isEmpty() && nums[maxDq.peekLast()] <= nums[right]) maxDq.pollLast();
            maxDq.offerLast(right);
            while (!minDq.isEmpty() && nums[minDq.peekLast()] >= nums[right]) minDq.pollLast();
            minDq.offerLast(right);

            while (left <= right) {
                int maxVal = nums[maxDq.peekFirst()];
                int minVal = nums[minDq.peekFirst()];
                long cost = 1L * (maxVal - minVal) * (right - left + 1);
                if (cost <= k) break;
                if (maxDq.peekFirst() == left) maxDq.pollFirst();
                if (minDq.peekFirst() == left) minDq.pollFirst();
                left++;
            }
            ans += right - left + 1;
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, long k, long output) {
        return output == countSubarrays(nums, k);
    }
}