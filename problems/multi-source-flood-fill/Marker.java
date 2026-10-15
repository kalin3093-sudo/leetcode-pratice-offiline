import java.util.*;
class Marker {
    public int[][] colorGrid(int n, int m, int[][] sources) {
        int[][] grid = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        
        // Initialize with sources
        for (int[] src : sources) {
            int r = src[0], c = src[1], color = src[2];
            grid[r][c] = color;
            visited[r][c] = true;
            q.offer(new int[]{r, c, color, 0}); // r, c, color, time
        }
        
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        Map<Integer, List<int[]>> byTime = new HashMap<>();
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], color = cur[2], time = cur[3];
            
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    grid[nr][nc] = color;
                    q.offer(new int[]{nr, nc, color, time + 1});
                } else if (grid[nr][nc] != color) {
                    // Check if reached at same time
                    // We need to track when each cell was first colored
                    // Actually, BFS ensures level-by-level, so if we're here at same level, it's same time
                    // But this approach is flawed. Let's use a different approach.
                }
            }
        }
        
        // Better approach: level-by-level BFS
        return colorGridBFS(n, m, sources);
    }
    
    private int[][] colorGridBFS(int n, int m, int[][] sources) {
        int[][] grid = new int[n][m];
        int[][] time = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(time[i], -1);
        
        Queue<int[]> q = new LinkedList<>();
        for (int[] src : sources) {
            int r = src[0], c = src[1], color = src[2];
            grid[r][c] = color;
            time[r][c] = 0;
            q.offer(new int[]{r, c, color});
        }
        
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        
        while (!q.isEmpty()) {
            int size = q.size();
            Map<Integer, List<int[]>> nextLevel = new HashMap<>();
            
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1], color = cur[2];
                
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if (time[nr][nc] != -1) continue; // already colored at earlier or same time
                    
                    nextLevel.computeIfAbsent(nr * m + nc, k -> new ArrayList<>()).add(new int[]{color});
                }
            }
            
            // Process next level: for each cell, pick max color
            for (Map.Entry<Integer, List<int[]>> e : nextLevel.entrySet()) {
                int pos = e.getKey();
                int nr = pos / m, nc = pos % m;
                int maxColor = 0;
                for (int[] c : e.getValue()) {
                    maxColor = Math.max(maxColor, c[0]);
                }
                grid[nr][nc] = maxColor;
                time[nr][nc] = time[nr][nc] == -1 ? 0 : time[nr][nc]; // just mark as visited
                q.offer(new int[]{nr, nc, maxColor});
            }
        }
        
        return grid;
    }

    public boolean isCorrect(int n, int m, int[][] sources, int[][] output) {
        int[][] expected = colorGrid(n, m, sources);
        if (expected.length != output.length) return false;
        for (int i = 0; i < expected.length; i++) {
            if (!Arrays.equals(expected[i], output[i])) return false;
        }
        return true;
    }
}