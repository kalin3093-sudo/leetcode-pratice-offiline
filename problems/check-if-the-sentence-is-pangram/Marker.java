import java.util.*;
class Marker {
  public boolean checkIfPangram(String sentence) {
    Set<Character> seen = new HashSet<>();

    for (final char c : sentence.toCharArray())
      seen.add(c);

    return seen.size() == 26;
  }

    public boolean isCorrect(String sentence, boolean output) {
        boolean expected = checkIfPangram(sentence);
        return expected == output;
    }
}
