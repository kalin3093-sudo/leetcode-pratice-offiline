import java.util.*;

class Marker {
    public int minimumFlips(int n) {
        String s = Integer.toBinaryString(n);
        int len = s.length();
        int flips = 0;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i)) {
                flips++;
            }
        }
        return flips * 2;
    }

    public boolean isCorrect(int n, int output) {
        return minimumFlips(n) == output;
    }
}