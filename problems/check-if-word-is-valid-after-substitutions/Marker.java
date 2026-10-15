import java.util.*;
class Marker {
  public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();

    for (final char c : s.toCharArray())
      if (c == 'c') {
        if (stack.size() < 2)
          return false;
        if (stack.peek() != 'b')
          return false;
        stack.pop();
        if (stack.peek() != 'a')
          return false;
        stack.pop();
      } else {
        stack.push(c);
      }

    return stack.isEmpty();
  }

    public boolean isCorrect(String s, boolean output) {
        boolean expected = isValid(s);
        return expected == output;
    }
}
