import java.util.*;
class Marker {
    public boolean scoreBalance(String s) {
        int n = s.length();
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (s.charAt(i) - 'a' + 1);
        }
        for (int i = 1; i < n; i++) {
            int left = prefix[i];
            int right = prefix[n] - prefix[i];
            if (left == right) return true;
        }
        return false;
    }

    public boolean isCorrect(String s, boolean output) {
        return scoreBalance(s) == output;
    }
}