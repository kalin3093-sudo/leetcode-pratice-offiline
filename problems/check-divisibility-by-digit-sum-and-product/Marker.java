import java.util.*;
class Marker {
    public boolean checkDivisibility(int n) {
        int ds = 0, dp = 1;
        for (int m = n; m > 0; m /= 10) {
            int d = m % 10;
            ds += d;
            dp *= d;
        }
        return n % (ds + dp) == 0;
    }

    public boolean isCorrect(int n, boolean output) {
        return checkDivisibility(n) == output;
    }
}
