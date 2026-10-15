import java.util.*;
class Marker {
  // Similar to 215. Kth Largest Element in an Array
  public String kthLargestNumber(String[] nums, int k) {
    Queue<String> minHeap = new PriorityQueue<>(
        Comparator.comparingInt(String::length).thenComparing(String::compareTo));

    for (final String num : nums) {
      minHeap.offer(num);
      if (minHeap.size() > k)
        minHeap.poll();
    }

    return minHeap.poll();
  }

    public boolean isCorrect(String[] nums, int k, String output) {
        String expected = kthLargestNumber(nums, k);
        return expected.equals(output);
    }
}
