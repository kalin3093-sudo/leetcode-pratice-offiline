import java.util.*;
class Marker {
  public String stringHash(String s, int k) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < s.length(); i += k) {
      int sumHash = 0;
      for (int j = i; j < i + k; ++j)
        sumHash += s.charAt(j) - 'a';
      sb.append((char) ('a' + sumHash % 26));
    }

    return sb.toString();
  }

    public boolean isCorrect(String s, int k, String output) {
        String expected = stringHash(s, k);
        return expected.equals(output);
    }
}
