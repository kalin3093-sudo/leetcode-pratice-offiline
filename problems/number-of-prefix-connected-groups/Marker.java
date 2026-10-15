import java.util.*;

class Marker {
    public int prefixConnected(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            if (w.length() >= k) cnt.merge(w.substring(0, k), 1, Integer::sum);
        }
        int ans = 0;
        for (int c : cnt.values()) {
            if (c >= 2) ans++;
        }
        return ans;
    }

    public boolean isCorrect(String[] words, int k, int output) {
        return output == prefixConnected(words, k);
    }
}
