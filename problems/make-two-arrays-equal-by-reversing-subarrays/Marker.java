import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public boolean canBeEqual(int[] target, int[] arr) {
    return Arrays.stream(arr)
        .boxed()
        .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
        .equals(Arrays.stream(target).boxed().collect(
            Collectors.groupingBy(Integer::intValue, Collectors.counting())));
  }

    public boolean isCorrect(int[] target, int[] arr, boolean output) {
        boolean expected = canBeEqual(target, arr);
        return expected == output;
    }
}
