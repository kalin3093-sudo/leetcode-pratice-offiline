import java.util.*;
class Marker {
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length, m = changeIndices.length;
        int lo = 1, hi = m, ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (canMark(nums, changeIndices, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }
    
    private boolean canMark(int[] nums, int[] changeIndices, int x) {
        int n = nums.length;
        int[] last = new int[n + 1];
        Arrays.fill(last, -1);
        for (int i = 0; i < x; i++) {
            last[changeIndices[i]] = i;
        }
        
        long timeNeeded = 0;
        for (int i = 1; i <= n; i++) {
            timeNeeded += nums[i - 1];
        }
        timeNeeded += n;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int marksAvailable = 0;
        
        for (int i = x - 1; i >= 0; i--) {
            int idx = changeIndices[i];
            if (i == last[idx] && nums[idx - 1] > 0) {
                pq.offer(nums[idx - 1] - 1);
            }
            marksAvailable++;
            if (!pq.isEmpty()) {
                timeNeeded -= pq.poll();
                marksAvailable--;
            }
        }
        return timeNeeded <= x;
    }

    public boolean isCorrect(int[] nums, int[] changeIndices, int output) {
        return earliestSecondToMarkIndices(nums, changeIndices) == output;
    }
}