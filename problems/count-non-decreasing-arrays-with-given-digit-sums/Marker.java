public class Marker {
    static final int MOD = 1_000_000_007;

    public int countArrays(int[] digitSum) {
        int maxV = 5000;
        int[] ds = new int[maxV + 1];
        for (int v = 0; v <= maxV; v++) {
            int s = 0, t = v;
            do { s += t % 10; t /= 10; } while (t > 0);
            ds[v] = s;
        }
        long[] dp = new long[maxV + 1];
        for (int v = 0; v <= maxV; v++) if (ds[v] == digitSum[0]) dp[v] = 1;
        for (int i = 1; i < digitSum.length; i++) {
            long[] pre = new long[maxV + 2];
            for (int v = 0; v <= maxV; v++) pre[v + 1] = (pre[v] + dp[v]) % MOD;
            long[] nd = new long[maxV + 1];
            for (int v = 0; v <= maxV; v++) if (ds[v] == digitSum[i]) nd[v] = pre[v + 1];
            dp = nd;
        }
        long ans = 0;
        for (int v = 0; v <= maxV; v++) ans = (ans + dp[v]) % MOD;
        return (int) ans;
    }

    public boolean isCorrect(int[] digitSum, int out) {
        return countArrays(digitSum) == out;
    }
}
