import java.util.*;
class Marker {
  public int findComplement(int num) {
    for (long i = 1; i <= num; i <<= 1)
      num ^= i;
    return num;
  }

    public boolean isCorrect(int num, int output) {
        int expected = findComplement(num);
        return expected == output;
    }
}
