import java.util.*;

class Marker {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 1;

        PriorityQueue<int[]>[] rowPQ = new PriorityQueue[m];
        PriorityQueue<int[]>[] colPQ = new PriorityQueue[n];
        for (int i = 0; i < m; i++) {
            rowPQ[i] = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        }
        for (int j = 0; j < n; j++) {
            colPQ[j] = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        }

        rowPQ[0].offer(new int[]{1, 0});
        colPQ[0].offer(new int[]{1, 0});

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                while (!rowPQ[i].isEmpty()) {
                    int[] top = rowPQ[i].peek();
                    int k = top[1];
                    if (k + grid[i][k] < j) {
                        rowPQ[i].poll();
                    } else {
                        break;
                    }
                }
                while (!colPQ[j].isEmpty()) {
                    int[] top = colPQ[j].peek();
                    int k = top[1];
                    if (k + grid[k][j] < i) {
                        colPQ[j].poll();
                    } else {
                        break;
                    }
                }

                int best = Integer.MAX_VALUE;
                if (!rowPQ[i].isEmpty()) best = Math.min(best, rowPQ[i].peek()[0]);
                if (!colPQ[j].isEmpty()) best = Math.min(best, colPQ[j].peek()[0]);

                if (best != Integer.MAX_VALUE) {
                    dist[i][j] = best + 1;
                    rowPQ[i].offer(new int[]{dist[i][j], j});
                    colPQ[j].offer(new int[]{dist[i][j], i});
                }
            }
        }
        return dist[m - 1][n - 1] == Integer.MAX_VALUE ? -1 : dist[m - 1][n - 1];
    }

    public boolean isCorrect(int[][] grid, int output) {
        return minimumVisitedCells(grid) == output;
    }
}