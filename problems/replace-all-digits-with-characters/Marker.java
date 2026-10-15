import java.util.*;
class Marker {
  public String replaceDigits(String s) {
    char[] chars = s.toCharArray();
    for (int i = 1; i < chars.length; i += 2)
      chars[i] += chars[i - 1] - '0';
    return String.valueOf(chars);
  }

    public boolean isCorrect(String s, String output) {
        String expected = replaceDigits(s);
        return expected.equals(output);
    }
}
