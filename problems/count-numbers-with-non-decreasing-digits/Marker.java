import java.util.*;

public class Marker {
    static final int MOD = 1_000_000_007;
    long[][][][] memo;
    int[] d;
    int b;

    long dp(int pos, int prev, int started, int tight) {
        if (pos == d.length) return 1;
        if (memo[pos][prev][started][tight] >= 0) return memo[pos][prev][started][tight];
        int limit = tight == 1 ? d[pos] : b - 1;
        long res = 0;
        for (int dig = 0; dig <= limit; dig++) {
            int ntight = tight == 1 && dig == limit ? 1 : 0;
            if (started == 0 && dig == 0) {
                res += dp(pos + 1, prev, 0, ntight);
            } else if (started == 0) {
                res += dp(pos + 1, dig, 1, ntight);
            } else if (dig >= prev) {
                res += dp(pos + 1, dig, 1, ntight);
            }
        }
        res %= MOD;
        memo[pos][prev][started][tight] = res;
        return res;
    }

    long f(String dec) {
        d = toBase(dec, b);
        memo = new long[d.length + 1][10][2][2];
        for (long[][][] a : memo)
            for (long[][] c : a)
                for (long[] e : c)
                    Arrays.fill(e, -1);
        return dp(0, 0, 0, 1);
    }

    static String decDecr(String s) {
        char[] c = s.toCharArray();
        int i = c.length - 1;
        while (i >= 0 && c[i] == '0') { c[i] = '9'; i--; }
        if (i >= 0) c[i]--;
        String t = new String(c);
        int j = 0;
        while (j < t.length() - 1 && t.charAt(j) == '0') j++;
        return t.substring(j);
    }

    static int[] toBase(String dec, int b) {
        java.util.List<Integer> digs = new java.util.ArrayList<>();
        String cur = dec;
        while (!cur.equals("0")) {
            StringBuilder q = new StringBuilder();
            int rem = 0;
            for (int i = 0; i < cur.length(); i++) {
                int v = rem * 10 + (cur.charAt(i) - '0');
                q.append(v / b);
                rem = v % b;
            }
            digs.add(rem);
            cur = q.toString();
            int j = 0;
            while (j < cur.length() - 1 && cur.charAt(j) == '0') j++;
            cur = cur.substring(j);
        }
        if (digs.isEmpty()) digs.add(0);
        int[] res = new int[digs.size()];
        for (int i = 0; i < res.length; i++) res[i] = digs.get(digs.size() - 1 - i);
        return res;
    }

    public int countNumbers(String l, String r, int b) {
        this.b = b;
        long hi = f(r);
        long lo = f(decDecr(l));
        return (int) ((hi - lo % MOD + MOD) % MOD);
    }

    public boolean isCorrect(String l, String r, int b, int out) {
        return countNumbers(l, r, b) == out;
    }
}
