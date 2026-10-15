import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int[] findIntersectionValues(int[] nums1, int[] nums2) {
    Set<Integer> nums1Set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
    Set<Integer> nums2Set = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
    final int ans1 = (int) Arrays.stream(nums1).filter(nums2Set::contains).count();
    final int ans2 = (int) Arrays.stream(nums2).filter(nums1Set::contains).count();
    return new int[] {ans1, ans2};
  }

    public boolean isCorrect(int[] nums1, int[] nums2, int[] output) {
        int[] expected = findIntersectionValues(nums1, nums2);
        return Arrays.equals(expected, output);
    }
}
