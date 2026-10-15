import java.util.*;
class Marker {
  public int maxRepeating(String sequence, String word) {
    int ans = 1;
    while (sequence.contains(word.repeat(ans)))
      ++ans;
    return ans - 1;
  }

    public boolean isCorrect(String sequence, String word, int output) {
        int expected = maxRepeating(sequence, word);
        return expected == output;
    }
}
