import java.util.*;
class Marker {
  public int minimumEffort(int[][] tasks) {
    int ans = 0;
    int prevSaved = 0;

    Arrays.sort(tasks, Comparator.comparingInt(task -> task[0] - task[1]));

    for (int[] task : tasks) {
      final int actual = task[0];
      final int minimum = task[1];
      if (prevSaved < minimum) {
        ans += minimum - prevSaved;
        prevSaved = minimum - actual;
      } else {
        prevSaved -= actual;
      }
    }

    return ans;
  }

    public boolean isCorrect(int[][] tasks, int output) {
        int expected = minimumEffort(tasks);
        return expected == output;
    }
}
