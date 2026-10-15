import java.util.*;
class Marker {
  public int lengthOfLastWord(String s) {
    int i = s.length() - 1;

    while (i >= 0 && s.charAt(i) == ' ')
      --i;
    final int lastIndex = i;
    while (i >= 0 && s.charAt(i) != ' ')
      --i;

    return lastIndex - i;
  }

    public boolean isCorrect(String s, int output) {
        int expected = lengthOfLastWord(s);
        return expected == output;
    }
}
