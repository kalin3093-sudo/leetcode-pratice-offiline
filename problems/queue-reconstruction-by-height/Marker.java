import java.util.*;
class Marker {
  public int[][] reconstructQueue(int[][] people) {
    List<int[]> ans = new ArrayList<>();

    Arrays.sort(people, Comparator.comparingInt((int[] person) -> - person[0])
                            .thenComparingInt((int[] person) -> person[1]));

    for (final int[] person : people)
      ans.add(person[1], person);

    return ans.stream().toArray(int[][] ::new);
  }

    public boolean isCorrect(int[][] people, int[][] output) {
        int[][] expected = reconstructQueue(people);
        return Arrays.deepEquals(expected, output);
    }
}
