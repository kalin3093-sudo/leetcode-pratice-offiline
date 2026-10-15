import java.util.*;
class Marker {
    public String lexSmallest(String s) {
        int n = s.length();
        String best = s; // will be overwritten since we must perform exactly one operation
        boolean first = true;
        for (int k = 1; k <= n; k++) {
            // reverse first k
            StringBuilder sb1 = new StringBuilder(s.substring(0, k)).reverse();
            sb1.append(s.substring(k));
            String cand1 = sb1.toString();
            if (first || cand1.compareTo(best) < 0) {
                best = cand1;
                first = false;
            }
            // reverse last k
            StringBuilder sb2 = new StringBuilder(s.substring(n - k)).reverse();
            String cand2 = s.substring(0, n - k) + sb2.toString();
            if (cand2.compareTo(best) < 0) {
                best = cand2;
            }
        }
        return best;
    }

    public boolean isCorrect(String s, String output) {
        return output.equals(lexSmallest(s));
    }
}