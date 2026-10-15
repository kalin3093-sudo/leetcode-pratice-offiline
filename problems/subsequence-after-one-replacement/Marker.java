import java.util.*;
class Marker {
    public boolean canMakeSubsequence(String s, String t) {
        int i = 0, j = 0, used = 0;
        int n = s.length(), m = t.length();
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else if (used == 0) {
                used = 1;
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == n;
    }
    public boolean isCorrect(String s, String t, boolean output) {
        return output == canMakeSubsequence(s, t);
    }
}
