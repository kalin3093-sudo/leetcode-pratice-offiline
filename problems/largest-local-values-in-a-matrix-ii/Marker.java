import java.util.*;
class Marker {
    public int countLocalMaximums(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        final int MAXV = 200;
        // prefix[v][i+1][j+1] = count of cells with value > v in matrix[0..i][0..j]
        int[][][] prefix = new int[MAXV + 1][n + 1][m + 1];
        for (int v = 0; v <= MAXV; v++) {
            for (int i = 0; i < n; i++) {
                int rowSum = 0;
                for (int j = 0; j < m; j++) {
                    rowSum += (matrix[i][j] > v) ? 1 : 0;
                    prefix[v][i + 1][j + 1] = prefix[v][i][j + 1] + rowSum;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = matrix[i][j];
                if (x == 0) continue;
                int r1 = Math.max(0, i - x);
                int r2 = Math.min(n - 1, i + x);
                int c1 = Math.max(0, j - x);
                int c2 = Math.min(m - 1, j + x);
                // Query prefix[x] for rectangle [r1, r2] x [c1, c2]
                int total = prefix[x][r2 + 1][c2 + 1] - prefix[x][r1][c2 + 1] - prefix[x][r2 + 1][c1] + prefix[x][r1][c1];
                // Subtract corner cells if they are inside and have value > x
                int[][] corners = {{i - x, j - x}, {i - x, j + x}, {i + x, j - x}, {i + x, j + x}};
                for (int[] cn : corners) {
                    int cr = cn[0], cc = cn[1];
                    if (cr >= 0 && cr < n && cc >= 0 && cc < m && matrix[cr][cc] > x) {
                        total--;
                    }
                }
                if (total == 0) count++;
            }
        }
        return count;
    }

    public boolean isCorrect(int[][] matrix, int output) {
        return output == countLocalMaximums(matrix);
    }
}