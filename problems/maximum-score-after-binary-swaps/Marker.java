import java.util.*;

class Marker {
    public long maximumScore(int[] nums, String s) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                if (!pq.isEmpty()) {
                    ans += pq.poll();
                }
            } else {
                pq.offer(nums[i]);
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, String s, long output) {
        return output == maximumScore(nums, s);
    }
}