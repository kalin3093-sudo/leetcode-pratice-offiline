import java.util.*;
class Marker {
  public int addedInteger(int[] nums1, int[] nums2) {
    return Arrays.stream(nums2).min().getAsInt() - Arrays.stream(nums1).min().getAsInt();
  }

    public boolean isCorrect(int[] nums1, int[] nums2, int output) {
        int expected = addedInteger(nums1, nums2);
        return expected == output;
    }
}
