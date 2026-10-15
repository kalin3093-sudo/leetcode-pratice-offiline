import java.util.*;
class Marker {
  public List<Integer> getRow(int rowIndex) {
    Integer[] ans = new Integer[rowIndex + 1];
    Arrays.fill(ans, 1);

    for (int i = 2; i < rowIndex + 1; ++i)
      for (int j = 1; j < i; ++j)
        ans[i - j] += ans[i - j - 1];

    return Arrays.asList(ans);
  }

    public boolean isCorrect(int rowIndex, List<Integer> output) {
        List<Integer> expected = getRow(rowIndex);
        return expected.equals(output);
    }
}
