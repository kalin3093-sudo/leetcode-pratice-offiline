import java.util.*;
class Marker {
  public String finalString(String s) {
    StringBuilder sb = new StringBuilder();
    Deque<Character> dq = new ArrayDeque<>();
    boolean inversed = false;

    for (final char c : s.toCharArray())
      if (c == 'i')
        inversed = !inversed;
      else if (inversed)
        dq.offerFirst(c);
      else
        dq.offerLast(c);

    while (!dq.isEmpty())
      sb.append(dq.pollFirst());

    return inversed ? sb.reverse().toString() : sb.toString();
  }

    public boolean isCorrect(String s, String output) {
        String expected = finalString(s);
        return expected.equals(output);
    }
}
