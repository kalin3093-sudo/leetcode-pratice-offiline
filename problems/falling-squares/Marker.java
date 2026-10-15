import java.util.*;
class Marker {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        List<int[]> intervals = new ArrayList<>();
        int maxH = 0;
        for (int[] p : positions) {
            int L = p[0];
            int R = p[0] + p[1];
            int h = p[1];
            int base = 0;
            for (int[] iv : intervals) {
                if (iv[0] < R && L < iv[1]) {
                    base = Math.max(base, iv[2]);
                }
            }
            int newH = base + h;
            intervals.add(new int[]{L, R, newH});
            maxH = Math.max(maxH, newH);
            ans.add(maxH);
        }
        return ans;
    }

    public boolean isCorrect(int[][] positions, List<Integer> output) {
        List<Integer> expected = fallingSquares(positions);
        return expected.equals(output);
    }
}