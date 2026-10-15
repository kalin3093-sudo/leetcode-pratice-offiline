import java.util.*;

class Marker {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int sx = -1, sy = -1;
        int litterCount = 0;
        Map<Integer, Integer> litterIndex = new HashMap<>();
        
        for (int i = 0; i < m; i++) {
            String row = classroom[i];
            for (int j = 0; j < n; j++) {
                char c = row.charAt(j);
                if (c == 'S') {
                    sx = i;
                    sy = j;
                } else if (c == 'L') {
                    litterIndex.put(i * n + j, litterCount++);
                }
            }
        }
        
        int fullMask = (1 << litterCount) - 1;
        int[][][] bestEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }
        
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy, 0, energy, 0});
        bestEnergy[sx][sy][0] = energy;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int x = state[0], y = state[1], mask = state[2], e = state[3], steps = state[4];
            
            if (mask == fullMask) return steps;
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                
                char c = classroom[nx].charAt(ny);
                if (c == 'X') continue;
                
                int ne = e - 1;
                if (ne < 0) continue;
                
                int nmask = mask;
                if (c == 'L') {
                    Integer idx = litterIndex.get(nx * n + ny);
                    if (idx != null) nmask |= (1 << idx);
                }
                if (c == 'R') {
                    ne = energy;
                }
                
                if (ne > bestEnergy[nx][ny][nmask]) {
                    bestEnergy[nx][ny][nmask] = ne;
                    q.add(new int[]{nx, ny, nmask, ne, steps + 1});
                }
            }
        }
        return -1;
    }

    public boolean isCorrect(String[] classroom, int energy, int output) {
        return minMoves(classroom, energy) == output;
    }
}