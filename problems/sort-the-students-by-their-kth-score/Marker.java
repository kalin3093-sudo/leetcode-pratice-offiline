import java.util.*;
class Marker {
  public int[][] sortTheStudents(int[][] score, int k) {
    Arrays.sort(score, Comparator.comparingInt(a -> - a[k]));
    return score;
  }

    public boolean isCorrect(int[][] score, int k, int[][] output) {
        int[][] expected = sortTheStudents(score, k);
        return Arrays.deepEquals(expected, output);
    }
}
