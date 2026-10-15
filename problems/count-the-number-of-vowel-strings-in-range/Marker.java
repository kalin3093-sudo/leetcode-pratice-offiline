import java.util.*;
class Marker {
  public int vowelStrings(String[] words, int left, int right) {
    return (int) Arrays.asList(words)
        .subList(left, right + 1)
        .stream()
        .filter(word -> isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1)))
        .count();
  }

  private boolean isVowel(char c) {
    return "aeiou".indexOf(c) != -1;
  }

    public boolean isCorrect(String[] words, int left, int right, int output) {
        int expected = vowelStrings(words, left, right);
        return expected == output;
    }
}
