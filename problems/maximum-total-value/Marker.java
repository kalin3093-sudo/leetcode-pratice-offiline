import java.util.*;
class Marker {
    static final int MOD = 1_000_000_007;
    
    public int maxTotalValue(int[] value, int[] decay, int m) {
        int n = value.length;
        long left = 1, right = 1_000_000_000L;
        
        while (left <= right) {
            long mid = (left + right) >> 1;
            if (countTerms(value, decay, mid) >= m) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        long threshold = right;
        long[] res = countAndSum(value, decay, threshold);
        long cnt = res[0];
        long sum = res[1];
        
        if (cnt > m) {
            sum -= (cnt - m) * threshold;
        }
        return (int) (sum % MOD);
    }
    
    private long countTerms(int[] value, int[] decay, long g) {
        long cnt = 0;
        for (int i = 0; i < value.length; i++) {
            if (value[i] < g) continue;
            long terms = (value[i] - g) / decay[i] + 1;
            cnt += terms;
            if (cnt > 1_000_000_000L) return cnt; // avoid overflow
        }
        return cnt;
    }
    
    private long[] countAndSum(int[] value, int[] decay, long g) {
        long cnt = 0;
        long sum = 0;
        for (int i = 0; i < value.length; i++) {
            if (value[i] < g) continue;
            long terms = (value[i] - g) / decay[i] + 1;
            cnt += terms;
            // Sum of arithmetic sequence: terms * (first + last) / 2
            // first = value[i], last = value[i] - decay[i] * (terms - 1)
            long last = value[i] - decay[i] * (terms - 1);
            sum += terms * (value[i] + last) / 2;
        }
        return new long[]{cnt, sum};
    }

    public boolean isCorrect(int[] value, int[] decay, int m, int output) {
        int expected = maxTotalValue(value, decay, m);
        return expected == output;
    }
}