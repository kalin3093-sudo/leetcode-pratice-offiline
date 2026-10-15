import java.util.*;
class Marker {
  public boolean canMakeSubsequence(String str1, String str2) {
    int i = 0; // str2's index

    for (final char c : str1.toCharArray())
      if (c == str2.charAt(i) || ('a' + ((c - 'a' + 1) % 26)) == str2.charAt(i))
        if (++i == str2.length())
          return true;

    return false;
  }

    public boolean isCorrect(String str1, String str2, boolean output) {
        boolean expected = canMakeSubsequence(str1, str2);
        return expected == output;
    }
}
