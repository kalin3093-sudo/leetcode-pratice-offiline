import java.util.*;

class Marker {
    private char[] digs;
    private int len;
    private long[][][][][][] memoCnt;
    private long[][][][][][] memoSum;

    public long totalWaviness(long num1, long num2) {
        return f(num2) - f(num1 - 1);
    }

    private long f(long bound) {
        if (bound <= 0) return 0;
        digs = Long.toString(bound).toCharArray();
        len = digs.length;
        memoCnt = new long[len][2][2][3][10][10];
        memoSum = new long[len][2][2][3][10][10];
        for (int i = 0; i < len; i++)
            for (int a = 0; a < 2; a++)
                for (int b = 0; b < 2; b++)
                    for (int c = 0; c < 3; c++)
                        for (int d = 0; d < 10; d++)
                            Arrays.fill(memoCnt[i][a][b][c][d], -1);
        return dfs(0, 1, 0, 0, 0, 0)[1];
    }

    private long[] dfs(int pos, int tight, int started, int placed, int p1, int p2) {
        if (pos == len) return new long[] { 1, 0 };
        if (memoCnt[pos][tight][started][placed][p1][p2] != -1)
            return new long[] { memoCnt[pos][tight][started][placed][p1][p2],
                    memoSum[pos][tight][started][placed][p1][p2] };
        int limit = tight == 1 ? digs[pos] - '0' : 9;
        long cnt = 0, sum = 0;
        for (int dig = 0; dig <= limit; dig++) {
            int ntight = tight == 1 && dig == limit ? 1 : 0;
            if (started == 0) {
                if (dig == 0) {
                    long[] r = dfs(pos + 1, ntight, 0, 0, 0, 0);
                    cnt += r[0];
                    sum += r[1];
                } else {
                    long[] r = dfs(pos + 1, ntight, 1, 1, dig, 0);
                    cnt += r[0];
                    sum += r[1];
                }
            } else {
                long add = 0;
                if (placed >= 2
                        && ((p1 > p2 && p1 > dig) || (p1 < p2 && p1 < dig)))
                    add = 1;
                long[] r = dfs(pos + 1, ntight, 1, Math.min(placed + 1, 2), dig, p1);
                cnt += r[0];
                sum += r[1] + add * r[0];
            }
        }
        memoCnt[pos][tight][started][placed][p1][p2] = cnt;
        memoSum[pos][tight][started][placed][p1][p2] = sum;
        return new long[] { cnt, sum };
    }

    public boolean isCorrect(long num1, long num2, long output) {
        return output == totalWaviness(num1, num2);
    }
}
