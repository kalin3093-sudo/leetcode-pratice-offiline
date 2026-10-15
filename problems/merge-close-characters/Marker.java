import java.util.*;
class Marker {
    public String mergeCharacters(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        boolean changed = true;
        
        while (changed) {
            changed = false;
            int n = sb.length();
            int bestLeft = -1, bestRight = -1;
            
            // Find the leftmost pair of equal characters with distance <= k
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n && j - i <= k; j++) {
                    if (sb.charAt(i) == sb.charAt(j)) {
                        bestLeft = i;
                        bestRight = j;
                        break;
                    }
                }
                if (bestLeft != -1) break;
            }
            
            if (bestLeft != -1) {
                sb.deleteCharAt(bestRight);
                changed = true;
            }
        }
        
        return sb.toString();
    }

    public boolean isCorrect(String s, int k, String output) {
        String expected = mergeCharacters(s, k);
        return expected.equals(output);
    }
}