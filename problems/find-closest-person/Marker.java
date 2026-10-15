import java.util.*;
class Marker {
    public int findClosest(int x, int y, int z) {
        int d1 = Math.abs(x - z);
        int d2 = Math.abs(y - z);
        if (d1 < d2) return 1;
        if (d2 < d1) return 2;
        return 0;
    }

    public boolean isCorrect(int x, int y, int z, int output) {
        return findClosest(x, y, z) == output;
    }
}