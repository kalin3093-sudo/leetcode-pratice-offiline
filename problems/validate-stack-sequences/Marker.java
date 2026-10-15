import java.util.*;
class Marker {
  public boolean validateStackSequences(int[] pushed, int[] popped) {
    Deque<Integer> stack = new ArrayDeque<>();
    int i = 0; // popped's index

    for (final int x : pushed) {
      stack.push(x);
      while (!stack.isEmpty() && stack.peek() == popped[i]) {
        stack.pop();
        ++i;
      }
    }

    return stack.isEmpty();
  }

    public boolean isCorrect(int[] pushed, int[] popped, boolean output) {
        boolean expected = validateStackSequences(pushed, popped);
        return expected == output;
    }
}
