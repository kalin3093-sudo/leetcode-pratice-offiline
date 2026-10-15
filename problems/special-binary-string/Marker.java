import java.util.*;
class Marker {
  public String makeLargestSpecial(String s) {
    List<String> specials = new ArrayList<>();
    int count = 0;

    for (int i = 0, j = 0; j < s.length(); ++j) {
      count += s.charAt(j) == '1' ? 1 : -1;
      if (count == 0) {
        specials.add("1" + makeLargestSpecial(s.substring(i + 1, j)) + "0");
        i = j + 1;
      }
    }

    Collections.sort(specials, Collections.reverseOrder());
    return String.join("", specials);
  }

    public boolean isCorrect(String s, String output) {
        String expected = makeLargestSpecial(s);
        return expected.equals(output);
    }
}
