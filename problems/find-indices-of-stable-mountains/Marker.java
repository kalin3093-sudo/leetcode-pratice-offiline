import java.util.*;
class Marker {
  public List<Integer> stableMountains(int[] height, int threshold) {
    List<Integer> ans = new ArrayList<>();

    for (int i = 1; i < height.length; ++i)
      if (height[i - 1] > threshold)
        ans.add(i);

    return ans;
  }

    public boolean isCorrect(int[] height, int threshold, List<Integer> output) {
        List<Integer> expected = stableMountains(height, threshold);
        return expected.equals(output);
    }
}
