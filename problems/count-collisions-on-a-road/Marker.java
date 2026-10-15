import java.util.*;
class Marker {
  public int countCollisions(String directions) {
    int ans = 0;
    int l = 0;
    int r = directions.length() - 1;

    while (l < directions.length() && directions.charAt(l) == 'L')
      ++l;

    while (r >= 0 && directions.charAt(r) == 'R')
      --r;

    for (int i = l; i <= r; ++i)
      if (directions.charAt(i) != 'S')
        ++ans;

    return ans;
  }

    public boolean isCorrect(String directions, int output) {
        int expected = countCollisions(directions);
        return expected == output;
    }
}
