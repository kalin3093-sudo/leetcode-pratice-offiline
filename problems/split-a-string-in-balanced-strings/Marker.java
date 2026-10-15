import java.util.*;
class Marker {
    public int balancedStringSplit(String s) {
        int bal = 0, count = 0;
        for (char c : s.toCharArray()) {
            bal += c == 'L' ? 1 : -1;
            if (bal == 0) count++;
        }
        return count;
    }

    public boolean isCorrect(String s, int output) {
        return balancedStringSplit(s) == output;
    }
}
