import java.util.*;
class Marker {
  public int closestTarget(String[] words, String target, int startIndex) {
    final int n = words.length;

    for (int i = 0; i < n; ++i) {
      if (words[(startIndex + i + n) % n].equals(target))
        return i;
      if (words[(startIndex - i + n) % n].equals(target))
        return i;
    }

    return -1;
  }

    public boolean isCorrect(String[] words, String target, int startIndex, int output) {
        int expected = closestTarget(words, target, startIndex);
        return expected == output;
    }
}
