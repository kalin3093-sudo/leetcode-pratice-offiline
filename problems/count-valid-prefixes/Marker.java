import java.util.*;

class Marker {
    public int countValidPrefixes(String s) {
        int zeros = 0, ones = 0;
        int ans = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
            else ones++;
            if (Math.abs(zeros - ones) <= 1) ans++;
        }
        return ans;
    }

    public boolean isCorrect(String s, int output) {
        return output == countValidPrefixes(s);
    }
}