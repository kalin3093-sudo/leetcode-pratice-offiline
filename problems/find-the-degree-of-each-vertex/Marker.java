import java.util.*;
class Marker {
    public int[] findDegrees(int[][] matrix) {
        int n = matrix.length;
        int[] deg = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                deg[i] += matrix[i][j];
            }
        }
        return deg;
    }

    public boolean isCorrect(int[][] matrix, int[] output) {
        return Arrays.equals(findDegrees(matrix), output);
    }
}