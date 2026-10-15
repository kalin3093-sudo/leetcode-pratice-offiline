import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int mostWordsFound(String[] sentences) {
    return 1 + Stream.of(sentences)
                   .mapToInt(s -> (int) s.chars().filter(c -> c == ' ').count())
                   .max()
                   .getAsInt();
  }

    public boolean isCorrect(String[] sentences, int output) {
        int expected = mostWordsFound(sentences);
        return expected == output;
    }
}
