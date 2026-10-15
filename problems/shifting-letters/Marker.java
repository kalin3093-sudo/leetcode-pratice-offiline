import java.util.*;
class Marker {
  public String shiftingLetters(String s, int[] shifts) {
    StringBuilder sb = new StringBuilder();

    for (int i = shifts.length - 2; i >= 0; --i)
      shifts[i] = (shifts[i] + shifts[i + 1]) % 26;

    for (int i = 0; i < s.length(); ++i)
      sb.append((char) ((s.charAt(i) - 'a' + shifts[i]) % 26 + 'a'));

    return sb.toString();
  }

    public boolean isCorrect(String s, int[] shifts, String output) {
        String expected = shiftingLetters(s, shifts);
        return expected.equals(output);
    }
}
