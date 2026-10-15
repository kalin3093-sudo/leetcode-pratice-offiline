import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int findMinimumTime(int[][] tasks) {
    final int MAX = 2000;
    boolean[] running = new boolean[MAX + 1];

    // Sort tasks by end.
    Arrays.sort(tasks, Comparator.comparingInt(task -> task[1]));

    for (int[] task : tasks) {
      final int start = task[0];
      final int end = task[1];
      final int duration = task[2];
      int neededDuration = duration;
      for (int i = start; i <= end; ++i)
        if (running[i])
          --neededDuration;
      // Greedily run the task as late as possible so that later tasks can run
      // simultaneously.
      for (int i = end; neededDuration > 0; --i) {
        if (!running[i]) {
          running[i] = true;
          --neededDuration;
        }
      }
    }

    return (int) IntStream.range(0, running.length)
        .mapToObj(i -> running[i])
        .filter(r -> r)
        .count();
  }

    public boolean isCorrect(int[][] tasks, int output) {
        int expected = findMinimumTime(tasks);
        return expected == output;
    }
}
