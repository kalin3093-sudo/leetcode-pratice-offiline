import java.util.*;
class Marker {
    public boolean checkGoodInteger(int n) {
        int ds = 0;
        long sq = 0;
        for (int m = n; m > 0; m /= 10) {
            int d = m % 10;
            ds += d;
            sq += (long) d * d;
        }
        return sq - ds >= 50;
    }

    public boolean isCorrect(int n, boolean output) {
        return checkGoodInteger(n) == output;
    }
}
