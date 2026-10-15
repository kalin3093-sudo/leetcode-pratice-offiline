import java.util.*;
class Marker {
  public long maxKelements(int[] nums, int k) {
    long ans = 0;
    Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    for (final int num : nums)
      maxHeap.offer(num);

    for (int i = 0; i < k; ++i) {
      final int num = maxHeap.poll();
      ans += num;
      maxHeap.offer((num + 2) / 3);
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int k, long output) {
        long expected = maxKelements(nums, k);
        return expected == output;
    }
}
