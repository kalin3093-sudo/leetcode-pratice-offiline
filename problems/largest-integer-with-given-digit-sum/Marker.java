import java.util.*;
class Marker {
    public int largestInteger(int n, int s) {
        if (s == 0) return 0;
        if (s > 9 * n) return -1;
        StringBuilder sb = new StringBuilder();
        int remaining = s;
        for (int i = 0; i < n; i++) {
            int d = Math.min(9, remaining);
            sb.append(d);
            remaining -= d;
        }
        return Integer.parseInt(sb.toString());
    }

    public boolean isCorrect(int n, int s, int output) {
        return output == largestInteger(n, s);
    }
}