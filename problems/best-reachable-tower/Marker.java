import java.util.*;
class Marker {
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int[] res = {-1, -1};
        int bestQ = -1;
        for (int[] t : towers) {
            int dist = Math.abs(t[0] - center[0]) + Math.abs(t[1] - center[1]);
            if (dist > radius) continue;
            if (t[2] > bestQ || (t[2] == bestQ && (t[0] < res[0] || (t[0] == res[0] && t[1] < res[1])))) {
                res = new int[]{t[0], t[1]};
                bestQ = t[2];
            }
        }
        return res;
    }

    public boolean isCorrect(int[][] towers, int[] center, int radius, int[] output) {
        if (output == null) return false;
        return Arrays.equals(bestTower(towers, center, radius), output);
    }
}
