import java.util.*;

public class Marker {

    public long countDistinct(long n) {
        String s = Long.toString(n);
        int len = s.length();
        long[][][] memo = new long[len][2][2];
        for (long[][] a : memo)
            for (long[] b : a)
                Arrays.fill(b, -1);
        return f(0, true, false, s, memo);
    }

    long f(int pos, boolean tight, boolean started, String s, long[][][] memo) {
        if (pos == s.length()) return started ? 1 : 0;
        int t = tight ? 1 : 0, st = started ? 1 : 0;
        if (memo[pos][t][st] != -1) return memo[pos][t][st];
        int limit = tight ? s.charAt(pos) - '0' : 9;
        long res = 0;
        if (!started) {
            res += f(pos + 1, tight && limit == 0, false, s, memo);
            for (int d = 1; d <= limit; d++) res += f(pos + 1, tight && d == limit, true, s, memo);
        } else {
            for (int d = 1; d <= limit; d++) res += f(pos + 1, tight && d == limit, true, s, memo);
        }
        memo[pos][t][st] = res;
        return res;
    }

    public boolean isCorrect(long n, long out) {
        return countDistinct(n) == out;
    }
}
