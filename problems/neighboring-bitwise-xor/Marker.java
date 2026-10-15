import java.util.*;
class Marker {
  public boolean doesValidArrayExist(int[] derived) {
    //      original = [O[0], O[1], ..., O[n - 1]]
    //       derived = [O[0]^O[1], O[1]^O[2], ..., O[n - 1]^O[0]]
    // XOR(derivedd) = 0
    return Arrays.stream(derived).reduce((a, b) -> a ^ b).getAsInt() == 0;
  }

    public boolean isCorrect(int[] derived, boolean output) {
        boolean expected = doesValidArrayExist(derived);
        return expected == output;
    }
}
