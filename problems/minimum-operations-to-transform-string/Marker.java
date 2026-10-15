import java.util.*;
class Marker {
    public int minOperations(String s) {
        int best = 0;
        boolean[] seen = new boolean[26];
        for (char c : s.toCharArray()) seen[c - 'a'] = true;
        for (int i = 0; i < 26; i++)
            if (seen[i]) best = Math.max(best, (26 - i) % 26);
        return best;
    }

    public boolean isCorrect(String s, int output) {
        return output == minOperations(s);
    }
}
