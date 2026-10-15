import java.util.*;

public class Marker {

    public long countPairs(String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < w.length(); i++) {
                sb.append((w.charAt(i) - w.charAt(0) + 26) % 26).append(',');
            }
            cnt.merge(sb.toString(), 1, Integer::sum);
        }
        long ans = 0;
        for (int v : cnt.values()) ans += (long) v * (v - 1) / 2;
        return ans;
    }

    public boolean isCorrect(String[] words, long out) {
        return countPairs(words) == out;
    }
}
