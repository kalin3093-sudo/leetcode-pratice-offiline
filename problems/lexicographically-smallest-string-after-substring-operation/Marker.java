import java.util.*;
class Marker {
  public String smallestString(String s) {
    char[] charArray = s.toCharArray();
    final int n = s.length();
    int i = 0;

    while (i < n && charArray[i] == 'a')
      ++i;
    if (i == n) {
      charArray[n - 1] = 'z';
      return new String(charArray);
    }

    while (i < n && charArray[i] != 'a')
      --charArray[i++];

    return new String(charArray);
  }

    public boolean isCorrect(String s, String output) {
        String expected = smallestString(s);
        return expected.equals(output);
    }
}
