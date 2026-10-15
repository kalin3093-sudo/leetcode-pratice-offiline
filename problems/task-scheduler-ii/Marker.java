import java.util.*;
class Marker {
  public long taskSchedulerII(int[] tasks, int space) {
    Map<Integer, Long> taskToNextAvailable = new HashMap<>();
    long ans = 0;

    for (final int task : tasks) {
      ans = Math.max(ans + 1, taskToNextAvailable.getOrDefault(task, 0L));
      taskToNextAvailable.put(task, ans + space + 1);
    }

    return ans;
  }

    public boolean isCorrect(int[] tasks, int space, long output) {
        long expected = taskSchedulerII(tasks, space);
        return expected == output;
    }
}
