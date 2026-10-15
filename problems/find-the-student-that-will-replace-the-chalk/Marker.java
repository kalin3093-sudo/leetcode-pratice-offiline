import java.util.*;
class Marker {
  public int chalkReplacer(int[] chalk, int k) {
    k %= Arrays.stream(chalk).asLongStream().sum();
    if (k == 0)
      return 0;

    for (int i = 0; i < chalk.length; ++i) {
      k -= chalk[i];
      if (k < 0)
        return i;
    }

    throw new IllegalArgumentException();
  }

    public boolean isCorrect(int[] chalk, int k, int output) {
        int expected = chalkReplacer(chalk, k);
        return expected == output;
    }
}
