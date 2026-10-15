import java.util.*;
class Marker {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    List<Integer> ans = new ArrayList<>();
    Map<Integer, Integer> numToNextGreater = new HashMap<>();
    Deque<Integer> stack = new ArrayDeque<>(); // a decreasing stack

    for (final int num : nums2) {
      while (!stack.isEmpty() && stack.peek() < num)
        numToNextGreater.put(stack.pop(), num);
      stack.push(num);
    }

    for (final int num : nums1)
      if (numToNextGreater.containsKey(num))
        ans.add(numToNextGreater.get(num));
      else
        ans.add(-1);

    return ans.stream().mapToInt(Integer::intValue).toArray();
  }

    public boolean isCorrect(int[] nums1, int[] nums2, int[] output) {
        int[] expected = nextGreaterElement(nums1, nums2);
        return Arrays.equals(expected, output);
    }
}
