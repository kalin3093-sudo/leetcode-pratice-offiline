import java.util.*;
class Marker {
  public boolean isCircularSentence(String sentence) {
    for (int i = 0; i < sentence.length(); ++i)
      if (sentence.charAt(i) == ' ' && sentence.charAt(i - 1) != sentence.charAt(i + 1))
        return false;
    return sentence.charAt(0) == sentence.charAt(sentence.length() - 1);
  }

    public boolean isCorrect(String sentence, boolean output) {
        boolean expected = isCircularSentence(sentence);
        return expected == output;
    }
}
