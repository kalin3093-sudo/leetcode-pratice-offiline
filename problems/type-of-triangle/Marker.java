import java.util.*;
class Marker {
  public String triangleType(int[] nums) {
    Arrays.sort(nums);
    if (nums[0] + nums[1] <= nums[2])
      return "none";
    if (nums[0] == nums[1] && nums[1] == nums[2])
      return "equilateral";
    if (nums[0] == nums[1] || nums[1] == nums[2])
      return "isosceles";
    return "scalene";
  }

    public boolean isCorrect(int[] nums, String output) {
        String expected = triangleType(nums);
        return expected.equals(output);
    }
}
