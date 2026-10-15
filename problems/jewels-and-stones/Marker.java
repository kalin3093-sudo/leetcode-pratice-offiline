import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int numJewelsInStones(String jewels, String stones) {
    int ans = 0;
    Set<Character> jewelsSet = jewels.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());

    for (final char stone : stones.toCharArray())
      if (jewelsSet.contains(stone))
        ++ans;

    return ans;
  }

    public boolean isCorrect(String jewels, String stones, int output) {
        int expected = numJewelsInStones(jewels, stones);
        return expected == output;
    }
}
