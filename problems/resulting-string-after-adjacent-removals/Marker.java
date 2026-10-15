import java.util.*;

class Marker {
  public String resultingString(String s) {
    Deque<Character> st = new ArrayDeque<>();
    for (char c : s.toCharArray()) {
      if (!st.isEmpty() && consecutive(st.peek(), c))
        st.pop();
      else
        st.push(c);
    }
    StringBuilder sb = new StringBuilder();
    while (!st.isEmpty())
      sb.append(st.pop());
    return sb.reverse().toString();
  }

  private boolean consecutive(char a, char b) {
    int d = Math.abs(a - b);
    return d == 1 || d == 25;
  }

  public boolean isCorrect(String s, String output) {
    return output != null && output.equals(resultingString(s));
  }
}
