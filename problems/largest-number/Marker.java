import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public String largestNumber(int[] nums) {
    final String s = Arrays.stream(nums)
                         .mapToObj(String::valueOf)
                         .sorted((a, b) -> (b + a).compareTo(a + b))
                         .collect(Collectors.joining(""));
    return s.startsWith("00") ? "0" : s;
  }

    public boolean isCorrect(int[] nums, String output) {
        String expected = largestNumber(nums);
        return expected.equals(output);
    }
}
