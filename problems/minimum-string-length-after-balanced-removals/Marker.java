import java.util.*;
class Marker {
    public int minLengthAfterRemovals(String s) {
        int countA = 0, countB = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') countA++;
            else countB++;
        }
        return Math.abs(countA - countB);
    }

    public boolean isCorrect(String s, int output) {
        return minLengthAfterRemovals(s) == output;
    }
}