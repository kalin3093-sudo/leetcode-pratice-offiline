import java.util.*;

class Marker {
    public long maxRectangleArea(int[] xCoord, int[] yCoord) {
        int n = xCoord.length;
        Set<Long> pointSet = new HashSet<>();
        Map<Integer, List<Integer>> xToYs = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int x = xCoord[i], y = yCoord[i];
            pointSet.add(((long) x << 32) | (y & 0xFFFFFFFFL));
            xToYs.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
        }
        for (List<Integer> ys : xToYs.values()) {
            Collections.sort(ys);
        }
        List<Integer> sortedX = new ArrayList<>(xToYs.keySet());
        long ans = -1;
        int xn = sortedX.size();
        for (int i = 0; i < xn; i++) {
            int x1 = sortedX.get(i);
            List<Integer> ys1 = xToYs.get(x1);
            for (int j = i + 1; j < xn; j++) {
                int x2 = sortedX.get(j);
                List<Integer> ys2 = xToYs.get(x2);
                List<Integer> commonYs = new ArrayList<>();
                int p1 = 0, p2 = 0;
                while (p1 < ys1.size() && p2 < ys2.size()) {
                    if (ys1.get(p1) == ys2.get(p2)) {
                        commonYs.add(ys1.get(p1));
                        p1++; p2++;
                    } else if (ys1.get(p1) < ys2.get(p2)) {
                        p1++;
                    } else {
                        p2++;
                    }
                }
                for (int k = 0; k + 1 < commonYs.size(); k++) {
                    int y1 = commonYs.get(k);
                    int y2 = commonYs.get(k + 1);
                    boolean ok = true;
                    for (int x = x1; x <= x2 && ok; x++) {
                        if (!xToYs.containsKey(x)) continue;
                        for (int y : xToYs.get(x)) {
                            if (y >= y1 && y <= y2) {
                                if (!((x == x1 || x == x2) && (y == y1 || y == y2))) {
                                    ok = false;
                                    break;
                                }
                            }
                        }
                    }
                    if (ok) {
                        long area = (long)(x2 - x1) * (y2 - y1);
                        ans = Math.max(ans, area);
                    }
                }
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] xCoord, int[] yCoord, long output) {
        return output == maxRectangleArea(xCoord, yCoord);
    }
}