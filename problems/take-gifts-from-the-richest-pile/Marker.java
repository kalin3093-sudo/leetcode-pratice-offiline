import java.util.*;
class Marker {
  public long pickGifts(int[] gifts, int k) {
    long ans = 0;
    Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    for (final int gift : gifts)
      maxHeap.offer(gift);

    for (int i = 0; i < k; ++i) {
      final int squaredMax = (int) Math.sqrt(maxHeap.poll());
      maxHeap.offer(squaredMax);
    }

    while (!maxHeap.isEmpty())
      ans += maxHeap.poll();

    return ans;
  }

    public boolean isCorrect(int[] gifts, int k, long output) {
        long expected = pickGifts(gifts, k);
        return expected == output;
    }
}
