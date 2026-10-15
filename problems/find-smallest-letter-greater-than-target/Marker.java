import java.util.*;
class Marker {
  public char nextGreatestLetter(char[] letters, char target) {
    int l = 0;
    int r = letters.length;

    while (l < r) {
      final int m = (l + r) / 2;
      if (letters[m] > target)
        r = m;
      else
        l = m + 1;
    }

    return letters[l % letters.length];
  }

    public boolean isCorrect(char[] letters, char target, char output) {
        char expected = nextGreatestLetter(letters, target);
        return expected == output;
    }
}
