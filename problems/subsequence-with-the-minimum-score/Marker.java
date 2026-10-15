import java.util.*;
class Marker {
    public int minimumScore(String s, String t) {
        int n = s.length(), m = t.length();
        int[] pre = new int[m];
        Arrays.fill(pre, n);
        int p = 0;
        for (int i = 0; i < n && p < m; i++) {
            if (s.charAt(i) == t.charAt(p)) pre[p++] = i;
        }
        if (p == m) return 0;
        int[] suf = new int[m];
        Arrays.fill(suf, -1);
        int q = m - 1;
        for (int i = n - 1; i >= 0 && q >= 0; i--) {
            if (s.charAt(i) == t.charAt(q)) suf[q--] = i;
        }
        if (q < 0) return 0;
        int ans = m;
        int i = -1;
        for (int j = 0; j <= m; j++) {
            int sj = (j == m) ? n : suf[j];
            if (sj == -1) continue;
            while (i + 1 <= j - 1 && pre[i + 1] < sj) i++;
            ans = Math.min(ans, j - i - 1);
        }
        return ans;
    }
    public boolean isCorrect(String s, String t, int output) {
        return output == minimumScore(s, t);
    }
}
