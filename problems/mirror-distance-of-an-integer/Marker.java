import java.util.*;
class Marker {
    public int mirrorDistance(int n) {
        int rev = 0;
        int orig = n;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return Math.abs(orig - rev);
    }

    public boolean isCorrect(int n, int output) {
        return mirrorDistance(n) == output;
    }
}