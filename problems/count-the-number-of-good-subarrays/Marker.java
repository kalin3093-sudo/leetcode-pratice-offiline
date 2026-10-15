import java.util.*;

class Marker {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        long pairs = 0;
        long ans = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            int x = nums[right];
            int cnt = freq.getOrDefault(x, 0);
            pairs += cnt;
            freq.put(x, cnt + 1);

            while (pairs >= k) {
                ans += n - right;
                int y = nums[left];
                int cntY = freq.get(y);
                pairs -= (cntY - 1);
                if (cntY == 1) freq.remove(y);
                else freq.put(y, cntY - 1);
                left++;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        return output == countGood(nums, k);
    }
}