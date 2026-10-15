import java.util.*;
class Marker {
    public int[][] specialGrid(int n) {
        int size = 1 << n;
        int[][] grid = new int[size][size];
        fill(grid, 0, 0, size, 0);
        return grid;
    }
    
    private void fill(int[][] grid, int r, int c, int size, int base) {
        if (size == 1) {
            grid[r][c] = base;
            return;
        }
        int half = size / 2;
        int quad = half * half;
        fill(grid, r, c + half, half, base);
        fill(grid, r + half, c + half, half, base + quad);
        fill(grid, r + half, c, half, base + 2 * quad);
        fill(grid, r, c, half, base + 3 * quad);
    }

    public boolean isCorrect(int n, int[][] output) {
        int[][] expected = specialGrid(n);
        if (output == null || output.length != expected.length) return false;
        for (int i = 0; i < expected.length; i++) {
            if (!Arrays.equals(expected[i], output[i])) return false;
        }
        return true;
    }
}