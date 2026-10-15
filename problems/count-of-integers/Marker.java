import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        return (countUpTo(num2, min_sum, max_sum) - countUpTo(subtractOne(num1), min_sum, max_sum) + MOD) % MOD;
    }

    private int countUpTo(String num, int min_sum, int max_sum) {
        if (num == null || num.isEmpty() || num.equals("0")) return 0;
        int n = num.length();
        int[][][] memo = new int[n][max_sum + 1][2];
        for (int i = 0; i < n; i++) {
            for (int s = 0; s <= max_sum; s++) {
                Arrays.fill(memo[i][s], -1);
            }
        }
        return dfs(num, 0, 0, true, min_sum, max_sum, memo);
    }

    private int dfs(String num, int pos, int sum, boolean tight, int min_sum, int max_sum, int[][][] memo) {
        if (sum > max_sum) return 0;
        if (pos == num.length()) {
            return (sum >= min_sum && sum <= max_sum) ? 1 : 0;
        }
        if (!tight && memo[pos][sum][0] != -1) return memo[pos][sum][0];
        if (tight && memo[pos][sum][1] != -1) return memo[pos][sum][1];

        int limit = tight ? num.charAt(pos) - '0' : 9;
        int res = 0;
        for (int d = 0; d <= limit; d++) {
            res = (res + dfs(num, pos + 1, sum + d, tight && d == limit, min_sum, max_sum, memo)) % MOD;
        }
        memo[pos][sum][tight ? 1 : 0] = res;
        return res;
    }

    private String subtractOne(String num) {
        char[] a = num.toCharArray();
        int i = a.length - 1;
        while (i >= 0 && a[i] == '0') {
            a[i] = '9';
            i--;
        }
        if (i < 0) return "0";
        a[i]--;
        String res = new String(a).replaceFirst("^0+", "");
        return res.isEmpty() ? "0" : res;
    }

    public boolean isCorrect(String num1, String num2, int min_sum, int max_sum, int output) {
        return output == count(num1, num2, min_sum, max_sum);
    }
}