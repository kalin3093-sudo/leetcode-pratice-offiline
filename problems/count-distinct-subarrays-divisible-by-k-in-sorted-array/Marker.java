import java.util.*;

public class Marker {

    public long numGoodSubarrays(int[] nums, int k) {
        int n = nums.length;
        Map<Long, Integer> residueCnt = new HashMap<>();
        residueCnt.put(0L, 1);
        long pre = 0;
        for (int i = 0; i < n; i++) {
            pre += nums[i];
            residueCnt.merge(Math.floorMod(pre, (long) k), 1, Integer::sum);
        }
        long totalPairs = 0;
        for (int c : residueCnt.values()) totalPairs += (long) c * (c - 1) / 2;
        long uniformPos = 0;
        long distinctUniform = 0;
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && nums[j] == nums[i]) j++;
            int runLen = j - i;
            long period = k / gcd(nums[i] % k, k);
            for (long L = period; L <= runLen; L += period) {
                uniformPos += runLen - L + 1;
                distinctUniform++;
            }
            i = j;
        }
        return totalPairs - uniformPos + distinctUniform;
    }

    long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public boolean isCorrect(int[] nums, int k, long out) {
        return numGoodSubarrays(nums, k) == out;
    }
}
