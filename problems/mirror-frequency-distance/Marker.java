import java.util.*;
class Marker {
    public int mirrorFrequency(String s) {
        int[] freq = new int[128];
        for (char c : s.toCharArray()) {
            freq[c]++;
        }
        
        int total = 0;
        boolean[] processed = new boolean[128];
        
        for (char c : s.toCharArray()) {
            if (processed[c]) continue;
            
            char m = mirror(c);
            if (processed[m]) continue;
            
            int diff = Math.abs(freq[c] - freq[m]);
            total += diff;
            processed[c] = true;
            processed[m] = true;
        }
        
        return total;
    }
    
    private char mirror(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) ('z' - (c - 'a'));
        } else if (c >= '0' && c <= '9') {
            return (char) ('9' - (c - '0'));
        }
        return c;
    }

    public boolean isCorrect(String s, int output) {
        return mirrorFrequency(s) == output;
    }
}