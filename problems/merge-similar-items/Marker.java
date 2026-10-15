import java.util.*;
class Marker {
  public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
    final int MAX = 1000;
    List<List<Integer>> ans = new ArrayList<>();
    int[] count = new int[MAX + 1];

    for (int[] item : items1)
      count[item[0]] += item[1];

    for (int[] item : items2)
      count[item[0]] += item[1];

    for (int i = 1; i <= MAX; ++i)
      if (count[i] > 0)
        ans.add(Arrays.asList(i, count[i]));

    return ans;
  }

    public boolean isCorrect(int[][] items1, int[][] items2, List<List<Integer>> output) {
        List<List<Integer>> expected = mergeSimilarItems(items1, items2);
        return expected.equals(output);
    }
}
