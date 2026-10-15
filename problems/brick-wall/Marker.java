import java.util.*;
class Marker {
  public int leastBricks(List<List<Integer>> wall) {
    int maxFreq = 0;
    Map<Integer, Integer> count = new HashMap<>();

    for (List<Integer> row : wall) {
      int prefix = 0;
      for (int i = 0; i < row.size() - 1; ++i) {
        prefix += row.get(i);
        maxFreq = Math.max(maxFreq, count.merge(prefix, 1, Integer::sum));
      }
    }

    return wall.size() - maxFreq;
  }

    public boolean isCorrect(List<List<Integer>> wall, int output) {
        int expected = leastBricks(wall);
        return expected == output;
    }
}
