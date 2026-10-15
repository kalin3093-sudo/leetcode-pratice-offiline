import java.util.*;
class Marker {
    public int minMoves(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) grid[i] = matrix[i].toCharArray();
        List<int[]>[] portals = new List[26];
        for (int i = 0; i < 26; i++) portals[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                if (c >= 'A' && c <= 'Z') portals[c - 'A'].add(new int[]{i, j});
            }
        }
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], -1);
        boolean[] portalUsed = new boolean[26];
        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{0, 0});
        dist[0][0] = 0;
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int r = cur[0], c = cur[1];
            if (r == m - 1 && c == n - 1) return dist[r][c];
            char cell = grid[r][c];
            if (cell >= 'A' && cell <= 'Z' && !portalUsed[cell - 'A']) {
                portalUsed[cell - 'A'] = true;
                for (int[] p : portals[cell - 'A']) {
                    int nr = p[0], nc = p[1];
                    if (dist[nr][nc] == -1) {
                        dist[nr][nc] = dist[r][c];
                        dq.addFirst(new int[]{nr, nc});
                    }
                }
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (grid[nr][nc] == '#') continue;
                if (dist[nr][nc] != -1) continue;
                dist[nr][nc] = dist[r][c] + 1;
                dq.add(new int[]{nr, nc});
            }
        }
        return -1;
    }
    public boolean isCorrect(String[] matrix, int output) {
        return minMoves(matrix) == output;
    }
}