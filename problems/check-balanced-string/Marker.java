import java.util.*;
class Marker {
  public boolean isBalanced(String num) {
    int balance = 0;
    int sign = 1;

    for (final char c : num.toCharArray()) {
      balance += sign * (c - '0');
      sign *= -1;
    }

    return balance == 0;
  }

    public boolean isCorrect(String num, boolean output) {
        boolean expected = isBalanced(num);
        return expected == output;
    }
}
