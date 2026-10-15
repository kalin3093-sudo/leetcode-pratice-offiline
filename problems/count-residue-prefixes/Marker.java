import java.util.*;

class Marker {
    public int residuePrefixes(String s) {
        Set<Character> seen = new HashSet<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            seen.add(s.charAt(i));
            if (seen.size() == (i + 1) % 3) {
                count++;
            }
        }
        return count;
    }

    public boolean isCorrect(String s, int output) {
        return output == residuePrefixes(s);
    }
}