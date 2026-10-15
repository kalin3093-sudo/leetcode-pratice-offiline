import java.util.*;
class Marker {
  public int miceAndCheese(int[] reward1, int[] reward2, int k) {
    Queue<Integer> minHeap = new PriorityQueue<>();

    for (int i = 0; i < reward1.length; ++i) {
      minHeap.offer(reward1[i] - reward2[i]);
      if (minHeap.size() == k + 1)
        minHeap.poll();
    }

    return Arrays.stream(reward2).sum() + minHeap.stream().mapToInt(Integer::intValue).sum();
  }

    public boolean isCorrect(int[] reward1, int[] reward2, int k, int output) {
        int expected = miceAndCheese(reward1, reward2, k);
        return expected == output;
    }
}
