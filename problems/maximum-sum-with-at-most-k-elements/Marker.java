import java.util.*;
class Marker {
  public long maxSum(int[][] grid, int[] limits, int k) {
    long ans = 0;
    Queue<Integer> minHeap = new PriorityQueue<>();

    for (int i = 0; i < grid.length; ++i) {
      Arrays.sort(grid[i]);
      for (int j = grid[i].length - 1; j >= grid[i].length - limits[i]; --j) {
        minHeap.offer(grid[i][j]);
        if (minHeap.size() == k + 1)
          minHeap.poll();
      }
    }

    while (!minHeap.isEmpty())
      ans += minHeap.poll();

    return ans;
  }

    public boolean isCorrect(int[][] grid, int[] limits, int k, long output) {
        long expected = maxSum(grid, limits, k);
        return expected == output;
    }
}
