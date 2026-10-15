import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public long maxSpending(int[][] values) {
    int[] sorted = Arrays.stream(values)
                       .flatMapToInt(Arrays::stream) //
                       .sorted()                     //
                       .toArray();                   //
    return LongStream.range(0, sorted.length)
        .map(i -> (i + 1) * sorted[(int) i]) //
        .sum();                              //
  }

    public boolean isCorrect(int[][] values, long output) {
        long expected = maxSpending(values);
        return expected == output;
    }
}
