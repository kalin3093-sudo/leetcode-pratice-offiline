import java.util.*;
class Marker {
    public int longestBalanced(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            for (int j = i; j < n; j++) {
                cnt[s.charAt(j) - 'a']++;
                int first = -1;
                boolean ok = true;
                for (int k = 0; k < 26; k++) {
                    if (cnt[k] > 0) {
                        if (first == -1) first = cnt[k];
                        else if (cnt[k] != first) { ok = false; break; }
                    }
                }
                if (ok) ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }

    public boolean isCorrect(String s, int output) {
        return output == longestBalanced(s);
    }
}