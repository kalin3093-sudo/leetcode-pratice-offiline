import java.util.*;
class Marker {
  public int minimumSum(int num) {
    char[] chars = String.valueOf(num).toCharArray();
    Arrays.sort(chars);
    return (chars[0] - '0') * 10 + (chars[2] - '0') + (chars[1] - '0') * 10 + (chars[3] - '0');
  }

    public boolean isCorrect(int num, int output) {
        int expected = minimumSum(num);
        return expected == output;
    }
}
