import java.util.*;
class Marker {
  public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
    // nums[minIndex] := the minimum number with enough index different from the current number
    int minIndex = 0;
    // nums[maxIndex] := the maximum number with enough index different from the current number
    int maxIndex = 0;

    for (int i = indexDifference; i < nums.length; ++i) {
      if (nums[i - indexDifference] < nums[minIndex])
        minIndex = i - indexDifference;
      if (nums[i - indexDifference] > nums[maxIndex])
        maxIndex = i - indexDifference;
      if (nums[i] - nums[minIndex] >= valueDifference)
        return new int[] {i, minIndex};
      if (nums[maxIndex] - nums[i] >= valueDifference)
        return new int[] {i, maxIndex};
    }

    return new int[] {-1, -1};
  }

    public boolean isCorrect(int[] nums, int indexDifference, int valueDifference, int[] output) {
        if (output == null || output.length != 2 || nums == null) return false;
        int i = output[0], j = output[1];
        if (i == -1 && j == -1) return !hasValidPair(nums, indexDifference, valueDifference);
        if (i < 0 || i >= nums.length || j < 0 || j >= nums.length) return false;
        if (Math.abs((long) i - j) < indexDifference) return false;
        if (Math.abs((long) nums[i] - nums[j]) < valueDifference) return false;
        return true;
    }

    private boolean hasValidPair(int[] nums, int indexDifference, int valueDifference) {
        long minVal = nums[0], maxVal = nums[0];
        for (int i = indexDifference; i < nums.length; ++i) {
            if (Math.abs((long) nums[i] - minVal) >= valueDifference) return true;
            if (Math.abs((long) maxVal - nums[i]) >= valueDifference) return true;
            minVal = Math.min(minVal, nums[i - indexDifference]);
            maxVal = Math.max(maxVal, nums[i - indexDifference]);
        }
        return false;
    }
}
