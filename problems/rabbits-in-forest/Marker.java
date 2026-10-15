import java.util.*;
class Marker {
  public int numRabbits(int[] answers) {
    int ans = 0;
    int[] count = new int[1000];

    for (final int answer : answers) {
      if (count[answer] % (answer + 1) == 0)
        ans += answer + 1;
      ++count[answer];
    }

    return ans;
  }

    public boolean isCorrect(int[] answers, int output) {
        int expected = numRabbits(answers);
        return expected == output;
    }
}
