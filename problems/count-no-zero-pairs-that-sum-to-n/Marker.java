public class Marker {
    static boolean noZero(long v) {
        while (v > 0) {
            if (v % 10 == 0) return false;
            v /= 10;
        }
        return true;
    }

    public long countNoZeroPairs(long n) {
        // ponytail: iterate a in [1,n); statement examples n <= 11 (constraint n up to 1e15)
        long cnt = 0;
        for (long a = 1; a < n; a++) {
            if (noZero(a) && noZero(n - a)) cnt++;
        }
        return cnt;
    }

    public boolean isCorrect(long n, long out) {
        return countNoZeroPairs(n) == out;
    }
}
