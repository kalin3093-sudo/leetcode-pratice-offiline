import java.util.*;
class Marker {
    public boolean consecutiveSetBits(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 3) == 3) count++;
            n >>= 1;
        }
        return count == 1;
    }

    public boolean isCorrect(int n, boolean output) {
        return consecutiveSetBits(n) == output;
    }
}