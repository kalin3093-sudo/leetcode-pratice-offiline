import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public List<String> splitWordsBySeparator(List<String> words, char separator) {
    return words.stream()
        .flatMap(word -> Arrays.stream(word.split("\\" + separator)))
        .filter(word -> !word.isEmpty())
        .collect(Collectors.toList());
  }

    public boolean isCorrect(List<String> words, char separator, List<String> output) {
        List<String> expected = splitWordsBySeparator(words, separator);
        return expected.equals(output);
    }
}
