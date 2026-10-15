import java.util.*;

class Marker {
    public boolean validDigit(int n, int x) {
        String s = String.valueOf(n);
        return s.contains(String.valueOf(x)) && s.charAt(0) - '0' != x;
    }

    public boolean isCorrect(int n, int x, boolean output) {
        return output == validDigit(n, x);
    }
}
