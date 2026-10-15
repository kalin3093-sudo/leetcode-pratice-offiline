import java.util.*;
class Marker {
    public int minCost(int n) {
        return n * (n - 1) / 2;
    }

    public boolean isCorrect(int n, int output) {
        int expected = minCost(n);
        return expected == output;
    }
}