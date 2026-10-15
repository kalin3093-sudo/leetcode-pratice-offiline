import java.util.*;
class Marker {
  public boolean reportSpam(String[] message, String[] bannedWords) {
    Set<String> bannedWordsSet = new HashSet<>(Arrays.asList(bannedWords));
    int count = 0;

    for (final String word : message)
      if (bannedWordsSet.contains(word) && ++count > 1)
        return true;

    return false;
  }

    public boolean isCorrect(String[] message, String[] bannedWords, boolean output) {
        boolean expected = reportSpam(message, bannedWords);
        return expected == output;
    }
}
