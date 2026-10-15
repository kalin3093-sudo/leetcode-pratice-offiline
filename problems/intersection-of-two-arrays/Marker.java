import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int[] intersection(int[] nums1, int[] nums2) {
    List<Integer> ans = new ArrayList<>();
    Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());

    for (final int num : nums2)
      if (set.remove(num))
        ans.add(num);

    return ans.stream().mapToInt(Integer::intValue).toArray();
  }

    public boolean isCorrect(int[] nums1, int[] nums2, int[] output) {
        int[] expected = intersection(nums1, nums2);
        return Arrays.equals(expected, output);
    }
}
