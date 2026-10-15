import java.util.*;
class Marker {
  public int uniqueMorseRepresentations(String[] words) {
    final String[] morse = {".-",   "-...", "-.-.", "-..",  ".",   "..-.", "--.",  "....", "..",
                            ".---", "-.-",  ".-..", "--",   "-.",  "---",  ".--.", "--.-", ".-.",
                            "...",  "-",    "..-",  "...-", ".--", "-..-", "-.--", "--.."};
    Set<String> transformations = new HashSet<>();

    for (final String word : words) {
      StringBuilder transformation = new StringBuilder();
      for (final char c : word.toCharArray())
        transformation.append(morse[c - 'a']);
      transformations.add(transformation.toString());
    }

    return transformations.size();
  }

    public boolean isCorrect(String[] words, int output) {
        int expected = uniqueMorseRepresentations(words);
        return expected == output;
    }
}
