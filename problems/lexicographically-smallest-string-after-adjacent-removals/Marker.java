import java.util.*;
class Marker {
    public String lexicographicallySmallestString(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        boolean[][] canRemove = new boolean[n][n];
        
        // Helper: check if two chars are consecutive (circular)
        java.util.function.BiPredicate<Character, Character> consec = (c1, c2) -> {
            int d = Math.abs(c1 - c2);
            return d == 1 || d == 25;
        };
        
        // DP: canRemove[i][j] = can remove s[i..j] completely
        for (int len = 2; len <= n; len += 2) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                // Option 1: remove first two if consecutive
                if (consec.test(a[i], a[i + 1]) && (i + 2 > j || canRemove[i + 2][j])) {
                    canRemove[i][j] = true;
                    continue;
                }
                // Option 2: remove i and k, with i+1..k-1 and k+1..j removable
                for (int k = i + 1; k <= j; k += 2) {
                    if (consec.test(a[i], a[k]) && 
                        (i + 1 > k - 1 || canRemove[i + 1][k - 1]) && 
                        (k + 1 > j || canRemove[k + 1][j])) {
                        canRemove[i][j] = true;
                        break;
                    }
                }
            }
        }
        
        // ans[i] = smallest string from s[i..n-1]
        String[] ans = new String[n + 1];
        ans[n] = "";
        for (int i = n - 1; i >= 0; i--) {
            String best = null;
            // Option: remove all
            if (i == n - 1 ? false : (i > n - 1 || canRemove[i][n - 1])) {
                best = "";
            }
            // Option: keep s[j], remove i..j-1
            for (int j = i; j < n; j++) {
                if (j == i || (i <= j - 1 && canRemove[i][j - 1])) {
                    String cand = a[j] + ans[j + 1];
                    if (best == null || cand.compareTo(best) < 0) {
                        best = cand;
                    }
                }
            }
            ans[i] = best != null ? best : "";
        }
        return ans[0];
    }

    public boolean isCorrect(String s, String output) {
        return output.equals(lexicographicallySmallestString(s));
    }
}