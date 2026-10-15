import java.util.*;
class Marker {
  public long countSubstrings(String s, char c) {
    final long freq = s.chars().filter(ch -> ch == c).count();
    return freq * (freq + 1) / 2;
  }

    public boolean isCorrect(String s, char c, long output) {
        long expected = countSubstrings(s, c);
        return expected == output;
    }
}
