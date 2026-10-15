import java.util.*;
class Marker {
    public int minSensors(int n, int m, int k) {
        int s = 2 * k + 1;
        long sensorsN = (n + s - 1) / s;
        long sensorsM = (m + s - 1) / s;
        return (int) (sensorsN * sensorsM);
    }

    public boolean isCorrect(int n, int m, int k, int output) {
        return minSensors(n, m, k) == output;
    }
}