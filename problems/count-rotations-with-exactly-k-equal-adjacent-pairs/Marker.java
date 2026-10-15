import java.util.*;

class Marker {
    public int countRotations(String s, int k) {
        int n = s.length();
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == s.charAt((i + 1) % n)) total++;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int excluded = (s.charAt(i) == s.charAt((i + 1) % n)) ? 1 : 0;
            int score = total - excluded;
            if (score == k) ans++;
        }
        return ans;
    }

    public boolean isCorrect(String s, int k, int output) {
        return output == countRotations(s, k);
    }
}