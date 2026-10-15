import java.util.*;
class Marker {
    public boolean isPalindromic(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            for (int b = 7; b >= 0; b--) sb.append((c >> b) & 1);
        }
        String t = sb.toString();
        int i = 0, j = t.length() - 1;
        while (i < j) {
            if (t.charAt(i++) != t.charAt(j--)) return false;
        }
        return true;
    }

    public boolean isCorrect(String s, boolean output) {
        return isPalindromic(s) == output;
    }
}
