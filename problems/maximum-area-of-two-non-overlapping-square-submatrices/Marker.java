import java.util.*;

class Marker {
    public int maxArea(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] prefix = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i + 1][j + 1] = mat[i][j] + prefix[i + 1][j] + prefix[i][j + 1] - prefix[i][j];
            }
        }

        int lo = 1, hi = Math.min(m, n), ans = 0;
        while (lo <= hi) {
            int k = (lo + hi) / 2;
            if (canPlaceTwo(mat, prefix, k)) {
                ans = k;
                lo = k + 1;
            } else {
                hi = k - 1;
            }
        }
        return ans * ans;
    }

    private boolean canPlaceTwo(int[][] mat, int[][] prefix, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int minR = m, maxR = -1, minC = n, maxC = -1;
        int count = 0;
        for (int i = 0; i + k <= m; i++) {
            for (int j = 0; j + k <= n; j++) {
                int sum = prefix[i + k][j + k] - prefix[i][j + k] - prefix[i + k][j] + prefix[i][j];
                if (sum == k * k) {
                    count++;
                    minR = Math.min(minR, i);
                    maxR = Math.max(maxR, i);
                    minC = Math.min(minC, j);
                    maxC = Math.max(maxC, j);
                }
            }
        }
        if (count < 2) return false;
        return (maxR - minR >= k) || (maxC - minC >= k);
    }

    public boolean isCorrect(int[][] mat, int output) {
        return output == maxArea(mat);
    }
}