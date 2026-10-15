import java.util.*;

class Marker {
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        return countUpTo(finish, limit, s) - countUpTo(start - 1, limit, s);
    }

    private long countUpTo(long x, int limit, String s) {
        if (x <= 0) return 0;
        String xs = String.valueOf(x);
        int n = xs.length();
        int m = s.length();

        long ans = 0;
        for (int len = m; len <= n; len++) {
            ans += countLen(len, xs, limit, s);
        }
        return ans;
    }

    private long countLen(int len, String xs, int limit, String s) {
        int m = s.length();
        int prefixLen = len - m;
        if (prefixLen == 0) {
            // Number is exactly s
            long val = Long.parseLong(s);
            long limitX = Long.parseLong(xs);
            return val <= limitX ? 1 : 0;
        }

        // Count prefixes of length prefixLen (first digit 1-9, others 0-limit)
        // such that prefix + s <= xs (if len == xs.length()) or any prefix (if len < xs.length())
        boolean tight = (len == xs.length());
        String limitStr = tight ? xs.substring(0, prefixLen) : null;
        return countPrefix(prefixLen, limitStr, limit);
    }

    private long countPrefix(int len, String limitStr, int limit) {
        if (len == 0) return 1;
        int[][] memo = new int[len + 1][2];
        for (int i = 0; i <= len; i++) Arrays.fill(memo[i], -1);
        return dfsPrefix(0, true, limitStr, limit, memo);
    }

    private long dfsPrefix(int pos, boolean tight, String limitStr, int limit, int[][] memo) {
        if (pos == limitStr.length()) return 1;
        int ti = tight ? 1 : 0;
        if (memo[pos][ti] != -1) return memo[pos][ti];

        int maxDigit = tight ? limitStr.charAt(pos) - '0' : limit;
        long res = 0;
        int start = (pos == 0) ? 1 : 0;
        for (int d = start; d <= maxDigit && d <= limit; d++) {
            boolean nextTight = tight && d == maxDigit;
            res += dfsPrefix(pos + 1, nextTight, limitStr, limit, memo);
        }
        memo[pos][ti] = (int) res;
        return res;
    }

    public boolean isCorrect(long start, long finish, int limit, String s, long output) {
        return output == numberOfPowerfulInt(start, finish, limit, s);
    }
}