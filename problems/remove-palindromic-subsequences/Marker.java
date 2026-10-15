import java.util.*;
class Marker {
  public int removePalindromeSub(String s) {
    return s.equals(new StringBuilder(s).reverse().toString()) ? 1 : 2;
  }

    public boolean isCorrect(String s, int output) {
        int expected = removePalindromeSub(s);
        return expected == output;
    }
}
