import java.util.*;
class Marker {
  public String firstPalindrome(String[] words) {
    for (final String word : words)
      if (isPalindrome(word))
        return word;
    return "";
  }

  private boolean isPalindrome(final String s) {
    int i = 0;
    int j = s.length() - 1;
    while (i < j)
      if (s.charAt(i++) != s.charAt(j--))
        return false;
    return true;
  }

    public boolean isCorrect(String[] words, String output) {
        String expected = firstPalindrome(words);
        return expected.equals(output);
    }
}
