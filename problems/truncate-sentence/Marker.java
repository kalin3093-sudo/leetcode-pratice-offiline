import java.util.*;
class Marker {
  public String truncateSentence(String s, int k) {
    String[] words = s.split(" ");
    String[] truncated = new String[k];

    for (int i = 0; i < k; ++i)
      truncated[i] = words[i];

    return String.join(" ", truncated);
  }

    public boolean isCorrect(String s, int k, String output) {
        String expected = truncateSentence(s, k);
        return expected.equals(output);
    }
}
