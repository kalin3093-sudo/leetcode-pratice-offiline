import java.util.*;

class Marker {
    public long countSubarrays(int[] nums, int k, int m) {
        int n = nums.length;
        long ans = 0;

        Map<Integer, Integer> freq1 = new HashMap<>();
        Map<Integer, Integer> freq2 = new HashMap<>();
        int distinct1 = 0, good1 = 0;
        int distinct2 = 0, good2 = 0;
        int r1 = 0, r2 = 0;

        for (int left = 0; left < n; left++) {
            if (left > 0) {
                int x = nums[left - 1];
                if (left - 1 < r1) {
                    int cnt = freq1.get(x);
                    if (cnt == m) good1--;
                    if (cnt == 1) { freq1.remove(x); distinct1--; }
                    else freq1.put(x, cnt - 1);
                }
                if (left - 1 < r2) {
                    int cnt = freq2.get(x);
                    if (cnt == m) good2--;
                    if (cnt == 1) { freq2.remove(x); distinct2--; }
                    else freq2.put(x, cnt - 1);
                }
            }

            while (r1 < n && (distinct1 < k || (distinct1 == k && good1 < k))) {
                int x = nums[r1];
                int cnt = freq1.getOrDefault(x, 0);
                if (cnt == 0) distinct1++;
                freq1.put(x, cnt + 1);
                if (cnt + 1 == m) good1++;

                cnt = freq2.getOrDefault(x, 0);
                if (cnt == 0) distinct2++;
                freq2.put(x, cnt + 1);
                if (cnt + 1 == m) good2++;

                r1++;
            }

            if (distinct1 != k || good1 != k) break;

            r2 = Math.max(r2, r1);
            while (r2 < n && distinct2 <= k) {
                int x = nums[r2];
                int cnt = freq2.getOrDefault(x, 0);
                if (cnt == 0) distinct2++;
                freq2.put(x, cnt + 1);
                if (cnt + 1 == m) good2++;
                r2++;
            }

            // r2 is first index where distinct2 > k, or n if never
            // valid right endpoints: r1-1 to r2-2 (if r2<n) or r2-1 (if r2==n)
            // count = (r2 == n ? n : r2 - 1) - (r1 - 1) + 1 = r2 - r1 + (r2 == n ? 1 : 0)
            if (r2 == n && distinct2 <= k) {
                ans += (long) n - r1 + 1;
            } else {
                ans += r2 - r1;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, int m, long output) {
        return output == countSubarrays(nums, k, m);
    }
}