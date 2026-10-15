import java.util.*;

class Marker {
    public String maximumXor(String s, String t) {
        int zeros = 0, ones = 0;
        for (char c : t.toCharArray()) {
            if (c == '0') zeros++;
            else ones++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '0' && ones > 0) {
                sb.append('1');
                ones--;
            } else if (c == '1' && zeros > 0) {
                sb.append('1');
                zeros--;
            } else if (c == '0') {
                sb.append('0');
                zeros--;
            } else {
                sb.append('0');
                ones--;
            }
        }
        return sb.toString();
    }

    public boolean isCorrect(String s, String t, String output) {
        return output.equals(maximumXor(s, t));
    }
}