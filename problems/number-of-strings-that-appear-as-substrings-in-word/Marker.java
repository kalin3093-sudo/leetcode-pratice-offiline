import java.util.*;
class Marker {
  public int numOfStrings(String[] patterns, String word) {
    return (int) Arrays.stream(patterns).filter(pattern -> word.contains(pattern)).count();
  }

    public boolean isCorrect(String[] patterns, String word, int output) {
        int expected = numOfStrings(patterns, word);
        return expected == output;
    }
}
