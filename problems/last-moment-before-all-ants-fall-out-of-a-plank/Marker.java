import java.util.*;
class Marker {
    public int getLastMoment(int n, int[] left, int[] right) {
        int ans = 0;
        for (int x : left) ans = Math.max(ans, x);
        for (int x : right) ans = Math.max(ans, n - x);
        return ans;
    }

    public boolean isCorrect(int n, int[] left, int[] right, int output) {
        return output == getLastMoment(n, left, right);
    }
}