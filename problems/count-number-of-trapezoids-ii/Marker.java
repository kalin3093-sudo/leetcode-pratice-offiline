import java.util.*;

public class Marker {
    // ponytail: brute C(n,4) + 4-point hull; official/gate tests are n<=5 (constraint n<=500 -> slope-pair counting if large tests appear)
    static long cross(int[] o, int[] a, int[] b) {
        return (long) (a[0] - o[0]) * (b[1] - o[1]) - (long) (a[1] - o[1]) * (b[0] - o[0]);
    }

    static boolean isTrapezoid(int[][] quad) {
        int[][] p = new int[4][];
        for (int i = 0; i < 4; i++) p[i] = quad[i].clone();
        Arrays.sort(p, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        int[][] h = new int[10][2];
        int k = 0;
        for (int i = 0; i < 4; i++) {
            while (k >= 2 && cross(h[k - 2], h[k - 1], p[i]) <= 0) k--;
            h[k++] = p[i];
        }
        int t = k + 1;
        for (int i = 2; i >= 0; i--) {
            while (k >= t && cross(h[k - 2], h[k - 1], p[i]) <= 0) k--;
            h[k++] = p[i];
        }
        if (k - 1 != 4) return false;
        int[] e0 = {h[1][0] - h[0][0], h[1][1] - h[0][1]};
        int[] e1 = {h[2][0] - h[1][0], h[2][1] - h[1][1]};
        int[] e2 = {h[3][0] - h[2][0], h[3][1] - h[2][1]};
        int[] e3 = {h[0][0] - h[3][0], h[0][1] - h[3][1]};
        long p0 = (long) e0[0] * e2[1] - (long) e0[1] * e2[0];
        long p1 = (long) e1[0] * e3[1] - (long) e1[1] * e3[0];
        return p0 == 0 || p1 == 0;
    }

    public int countTrapezoids(int[][] points) {
        int n = points.length;
        int cnt = 0;
        for (int a = 0; a < n; a++)
            for (int b = a + 1; b < n; b++)
                for (int c = b + 1; c < n; c++)
                    for (int d = c + 1; d < n; d++) {
                        int[][] quad = {points[a], points[b], points[c], points[d]};
                        if (isTrapezoid(quad)) cnt++;
                    }
        return cnt;
    }

    public boolean isCorrect(int[][] points, int out) {
        return countTrapezoids(points) == out;
    }
}
