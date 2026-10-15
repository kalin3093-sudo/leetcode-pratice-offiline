import java.util.*;
class Marker {
    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][][][] dist = new int[m][n][4][k + 1];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int d = 0; d < 4; d++)
                    Arrays.fill(dist[i][j][d], Integer.MAX_VALUE);

        // Directions: 0=up, 1=down, 2=left, 3=right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        // Start with no direction (-1), 0 turns, cost = grid[0][0]
        // We'll push initial moves from (0,0) with 0 turns
        for (int d = 0; d < 4; d++) {
            int nr = 0 + dr[d], nc = 0 + dc[d];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                int cost = grid[0][0] + grid[nr][nc];
                if (cost < dist[nr][nc][d][0]) {
                    dist[nr][nc][d][0] = cost;
                    pq.offer(new State(nr, nc, d, 0, cost));
                }
            }
        }

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.cost != dist[cur.r][cur.c][cur.dir][cur.turns]) continue;
            if (cur.r == m - 1 && cur.c == n - 1) return cur.cost;

            for (int nd = 0; nd < 4; nd++) {
                int nr = cur.r + dr[nd];
                int nc = cur.c + dc[nd];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                int nTurns = cur.turns + (nd == cur.dir ? 0 : 1);
                if (nTurns > k) continue;

                int nCost = cur.cost + grid[nr][nc];
                if (nCost < dist[nr][nc][nd][nTurns]) {
                    dist[nr][nc][nd][nTurns] = nCost;
                    pq.offer(new State(nr, nc, nd, nTurns, nCost));
                }
            }
        }
        return -1;
    }

    static class State {
        int r, c, dir, turns, cost;
        State(int r, int c, int dir, int turns, int cost) {
            this.r = r; this.c = c; this.dir = dir; this.turns = turns; this.cost = cost;
        }
    }

    public boolean isCorrect(int[][] grid, int k, int output) {
        int expected = minCost(grid, k);
        return expected == output;
    }
}