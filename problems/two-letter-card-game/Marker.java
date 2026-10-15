import java.util.*;

class Marker {
    public int score(String[] cards, char x) {
        int both = 0;
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (String c : cards) {
            boolean a = c.charAt(0) == x;
            boolean b = c.charAt(1) == x;
            if (a && b) both++;
            else if (a) cnt1[c.charAt(1) - 'a']++;
            else if (b) cnt2[c.charAt(0) - 'a']++;
        }
        int best = 0;
        for (int i = 0; i <= both; i++) {
            best = Math.max(best, solve(cnt1, i) + solve(cnt2, both - i));
        }
        return best;
    }

    private int solve(int[] cnt, int have) {
        int S = 0, c0 = 0;
        for (int i = 0; i < 26; i++) {
            S += cnt[i];
            if (cnt[i] > c0) c0 = cnt[i];
        }
        if (S == 0) return 0;
        int u = Math.min(have, S);
        if (c0 - u > S - c0) return u + (S - c0);
        return u + (S - u) / 2;
    }

    public boolean isCorrect(String[] cards, char x, int output) {
        return output == score(cards, x);
    }
}
