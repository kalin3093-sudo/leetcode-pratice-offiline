import java.util.*;
class Marker {
    public int distinctPoints(String s, int k) {
        int n = s.length();
        int[] px = new int[n + 1];
        int[] py = new int[n + 1];
        for (int i = 0; i < n; i++) {
            px[i + 1] = px[i];
            py[i + 1] = py[i];
            char c = s.charAt(i);
            if (c == 'L') px[i + 1]--;
            else if (c == 'R') px[i + 1]++;
            else if (c == 'U') py[i + 1]++;
            else if (c == 'D') py[i + 1]--;
        }
        Set<Long> seen = new HashSet<>();
        for (int i = 0; i + k <= n; i++) {
            int x = px[i] + (px[n] - px[i + k]);
            int y = py[i] + (py[n] - py[i + k]);
            seen.add(((long) x << 32) ^ (y & 0xffffffffL));
        }
        return seen.size();
    }

    public boolean isCorrect(String s, int k, int output) {
        return distinctPoints(s, k) == output;
    }
}