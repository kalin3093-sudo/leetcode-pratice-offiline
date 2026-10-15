import java.util.*;
class Marker {
  public boolean areNumbersAscending(String s) {
    int prev = 0;

    for (final String token : s.split(" "))
      if (Character.isDigit(token.charAt(0))) {
        final int num = Integer.parseInt(token);
        if (num <= prev)
          return false;
        prev = num;
      }

    return true;
  }

    public boolean isCorrect(String s, boolean output) {
        boolean expected = areNumbersAscending(s);
        return expected == output;
    }
}
