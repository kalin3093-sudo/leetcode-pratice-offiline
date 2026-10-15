import java.util.*;
class Marker {
  public String makeSmallestPalindrome(String s) {
    char[] chars = s.toCharArray();
    for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
      final char minChar = (char) Math.min(chars[i], chars[j]);
      chars[i] = minChar;
      chars[j] = minChar;
    }
    return new String(chars);
  }

    public boolean isCorrect(String s, String output) {
        String expected = makeSmallestPalindrome(s);
        return expected.equals(output);
    }
}
