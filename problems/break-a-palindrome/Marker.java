import java.util.*;
class Marker {
  public String breakPalindrome(String palindrome) {
    if (palindrome.length() == 1)
      return "";

    StringBuilder sb = new StringBuilder(palindrome);

    for (int i = 0; i < palindrome.length() / 2; ++i)
      if (palindrome.charAt(i) != 'a') {
        sb.setCharAt(i, 'a');
        return sb.toString();
      }

    sb.setCharAt(sb.length() - 1, 'b');
    return sb.toString();
  }

    public boolean isCorrect(String palindrome, String output) {
        String expected = breakPalindrome(palindrome);
        return expected.equals(output);
    }
}
