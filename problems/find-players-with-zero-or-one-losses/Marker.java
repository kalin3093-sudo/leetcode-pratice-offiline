import java.util.*;
class Marker {
  public List<List<Integer>> findWinners(int[][] matches) {
    List<List<Integer>> ans = Arrays.asList(new ArrayList<>(), new ArrayList<>());
    Map<Integer, Integer> lossesCount = new TreeMap<>();

    for (int[] m : matches) {
      final int winner = m[0];
      final int loser = m[1];
      if (!lossesCount.containsKey(winner))
        lossesCount.put(winner, 0);
      lossesCount.merge(loser, 1, Integer::sum);
    }

    for (final int player : lossesCount.keySet()) {
      final int nLosses = lossesCount.get(player);
      if (nLosses < 2)
        ans.get(nLosses).add(player);
    }

    return ans;
  }

    public boolean isCorrect(int[][] matches, List<List<Integer>> output) {
        List<List<Integer>> expected = findWinners(matches);
        return expected.equals(output);
    }
}
