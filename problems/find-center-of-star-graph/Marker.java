import java.util.*;
class Marker {
  public int findCenter(int[][] edges) {
    return edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] //
        ? edges[0][0]
        : edges[0][1];
  }

    public boolean isCorrect(int[][] edges, int output) {
        int expected = findCenter(edges);
        return expected == output;
    }
}
