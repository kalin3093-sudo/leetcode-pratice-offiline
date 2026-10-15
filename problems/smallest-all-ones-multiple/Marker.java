import java.util.*;

class Marker {
    public int minAllOneMultiple(int k) {
        if (k % 2 == 0 || k % 5 == 0)
            return -1;
        boolean[] seen = new boolean[k];
        int rem = 1 % k;
        int len = 1;
        while (rem != 0) {
            if (seen[rem])
                return -1;
            seen[rem] = true;
            rem = (rem * 10 + 1) % k;
            len++;
        }
        return len;
    }

    public boolean isCorrect(int k, int output) {
        return minAllOneMultiple(k) == output;
    }
}
