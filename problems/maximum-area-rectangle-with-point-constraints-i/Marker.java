import java.util.*;

class Marker {
    public int maxRectangleArea(int[][] points) {
        Set<String> pointSet = new HashSet<>();
        for (int[] p : points) {
            pointSet.add(p[0] + "," + p[1]);
        }
        int ans = -1;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 == x2 || y1 == y2) continue;
                if (!pointSet.contains(x1 + "," + y2)) continue;
                if (!pointSet.contains(x2 + "," + y1)) continue;
                int xMin = Math.min(x1, x2), xMax = Math.max(x1, x2);
                int yMin = Math.min(y1, y2), yMax = Math.max(y1, y2);
                boolean ok = true;
                for (int[] p : points) {
                    int x = p[0], y = p[1];
                    if (x >= xMin && x <= xMax && y >= yMin && y <= yMax) {
                        if (!((x == x1 && y == y1) || (x == x1 && y == y2) || (x == x2 && y == y1) || (x == x2 && y == y2))) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) {
                    ans = Math.max(ans, (xMax - xMin) * (yMax - yMin));
                }
            }
        }
        return ans;
    }

    public boolean isCorrect(int[][] points, int output) {
        return output == maxRectangleArea(points);
    }
}