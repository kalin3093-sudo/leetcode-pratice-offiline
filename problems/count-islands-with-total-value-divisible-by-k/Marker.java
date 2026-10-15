public class Marker {

    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long sum = grid[i][j];
                    grid[i][j] = 0;
                    int[] qi = new int[m * n], qj = new int[m * n];
                    int head = 0, tail = 0;
                    qi[tail] = i; qj[tail] = j; tail++;
                    while (head < tail) {
                        int ci = qi[head], cj = qj[head];
                        head++;
                        int[] di = {-1, 1, 0, 0}, dj = {0, 0, -1, 1};
                        for (int d = 0; d < 4; d++) {
                            int ni = ci + di[d], nj = cj + dj[d];
                            if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] > 0) {
                                sum += grid[ni][nj];
                                grid[ni][nj] = 0;
                                qi[tail] = ni; qj[tail] = nj; tail++;
                            }
                        }
                    }
                    if (sum % k == 0) cnt++;
                }
            }
        }
        return cnt;
    }

    public boolean isCorrect(int[][] grid, int k, int out) {
        return countIslands(grid, k) == out;
    }
}
