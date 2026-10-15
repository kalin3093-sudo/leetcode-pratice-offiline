import java.util.*;
class UnionFind {
  public UnionFind(int n) {
    id = new int[n];
    rank = new int[n];
    for (int i = 0; i < n; ++i)
      id[i] = i;
  }

  public void unionByRank(int u, int v) {
    final int i = find(u);
    final int j = find(v);
    if (i == j)
      return;
    if (rank[i] < rank[j]) {
      id[i] = j;
    } else if (rank[i] > rank[j]) {
      id[j] = i;
    } else {
      id[i] = j;
      ++rank[j];
    }
  }

  public int find(int u) {
    return id[u] == u ? u : (id[u] = find(id[u]));
  }

  private int[] id;
  private int[] rank;
}

class Marker {
  public boolean canReachCorner(int X, int Y, int[][] circles) {
    final int n = circles.length;
    // Add two virtual nodes, where node n represents (0, 0) and node n + 1
    // represents (X, Y).
    UnionFind uf = new UnionFind(n + 2);

    // Iterate through each circle.
    for (int i = 0; i < n; ++i) {
      final int x = circles[i][0];
      final int y = circles[i][1];
      final int r = circles[i][2];
      // ponytail: distance to edge SEGMENT (centers may sit outside the rect);
      // naive axis checks over-unite when the center is out of bounds
      final long dx = x - Math.max(0, Math.min(x, X));
      final long dy = y - Math.max(0, Math.min(y, Y));
      // Union the current circle with the node (0, 0) if the circle overlaps
      // with the left or top edges.
      if ((long) x * x + dy * dy <= (long) r * r ||
          dx * dx + (long) (Y - y) * (Y - y) <= (long) r * r)
        uf.unionByRank(i, n);
      // Union the current circle with the node (X, Y) if the circle overlaps
      // with the right or bottom edges.
      if ((long) (X - x) * (X - x) + dy * dy <= (long) r * r ||
          dx * dx + (long) y * y <= (long) r * r)
        uf.unionByRank(i, n + 1);
      // Union the current circle with previous circles if they overlap.
      for (int j = 0; j < i; j++) {
        final int x2 = circles[j][0];
        final int y2 = circles[j][1];
        final int r2 = circles[j][2];
        if ((long) (x - x2) * (x - x2) + (long) (y - y2) * (y - y2) <= (long) (r + r2) * (r + r2))
          uf.unionByRank(i, j);
      }
    }

    // If nodes (0, 0) and (X, Y) are in the same union set, that means there's
    // a path of overlapping circles that connects the left or top edges to the
    // right or bottom edges, implying that (0, 0) cannot reach (X, Y).
    return uf.find(n) != uf.find(n + 1);
  }

    public boolean isCorrect(int xCorner, int yCorner, int[][] circles, boolean output) {
        boolean expected = canReachCorner(xCorner, yCorner, circles);
        return expected == output;
    }
}
