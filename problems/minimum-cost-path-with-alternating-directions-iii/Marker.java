import java.util.*;
class Marker {
    public long minCost(int m, int n, int[][] penalty) {
        long[][][] dist = new long[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j][0] = Long.MAX_VALUE;
                dist[i][j][1] = Long.MAX_VALUE;
            }
        }
        dist[0][0][1] = 1; // start at (0,0), next action is odd (1)
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(s -> s.d));
        pq.offer(new State(0, 0, 1, 1));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.d != dist[cur.r][cur.c][cur.p]) continue;
            if (cur.r == m-1 && cur.c == n-1) return cur.d;

            // Wait action
            long nd = cur.d + penalty[cur.r][cur.c];
            int np = 1 - cur.p;
            if (nd < dist[cur.r][cur.c][np]) {
                dist[cur.r][cur.c][np] = nd;
                pq.offer(new State(cur.r, cur.c, np, nd));
            }

            // Move actions
            for (int k = 0; k < 4; k++) {
                int nr = cur.r + dr[k];
                int nc = cur.c + dc[k];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                boolean follow;
                if (cur.p == 1) { // odd action: right(3) or down(1)
                    follow = (k == 1 || k == 3);
                } else { // even action: left(2) or up(0)
                    follow = (k == 0 || k == 2);
                }

                long cost = (long)(nr + 1) * (nc + 1);
                if (!follow) cost += penalty[cur.r][cur.c];

                nd = cur.d + cost;
                if (nd < dist[nr][nc][np]) {
                    dist[nr][nc][np] = nd;
                    pq.offer(new State(nr, nc, np, nd));
                }
            }
        }
        return -1;
    }

    static class State {
        int r, c, p;
        long d;
        State(int r, int c, int p, long d) {
            this.r = r; this.c = c; this.p = p; this.d = d;
        }
    }

    public boolean isCorrect(int m, int n, int[][] penalty, long output) {
        long expected = minCost(m, n, penalty);
        return expected == output;
    }
}