import java.util.*;
class Marker {
  public int[] memLeak(int memory1, int memory2) {
    int i = 1;

    while (memory1 >= i || memory2 >= i) {
      if (memory1 >= memory2)
        memory1 -= i;
      else
        memory2 -= i;
      ++i;
    }

    return new int[] {i, memory1, memory2};
  }

    public boolean isCorrect(int memory1, int memory2, int[] output) {
        int[] expected = memLeak(memory1, memory2);
        return Arrays.equals(expected, output);
    }
}
