import java.util.*;
class Marker {
  public String removeDigit(String number, char digit) {
    for (int i = 0; i + 1 < number.length(); ++i)
      if (number.charAt(i) == digit && digit < number.charAt(i + 1))
        return new StringBuilder(number).deleteCharAt(i).toString();
    return new StringBuilder(number).deleteCharAt(number.lastIndexOf(digit)).toString();
  }

    public boolean isCorrect(String number, char digit, String output) {
        String expected = removeDigit(number, digit);
        return expected.equals(output);
    }
}
