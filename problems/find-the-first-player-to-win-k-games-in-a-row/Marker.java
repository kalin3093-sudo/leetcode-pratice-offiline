import java.util.*;
class Marker {
  // Similar to 1535. Find the Winner of an Array Game
  public int findWinningPlayer(int[] skills, int k) {
    int ans = 0;
    int wins = 0;

    for (int i = 1; i < skills.length && wins < k; ++i)
      if (skills[i] > skills[ans]) {
        ans = i;
        wins = 1;
      } else {
        ++wins;
      }

    return ans;
  }

    public boolean isCorrect(int[] skills, int k, int output) {
        int expected = findWinningPlayer(skills, k);
        return expected == output;
    }
}
