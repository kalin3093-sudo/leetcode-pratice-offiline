import java.util.*;
class Marker {
  public String bestHand(int[] ranks, char[] suits) {
    if (new String(suits).chars().allMatch(s -> s == suits[0]))
      return "Flush";

    final int MAX = 13;
    int[] count = new int[MAX + 1];

    for (final int rank : ranks)
      ++count[rank];

    final int mx = Arrays.stream(count).max().getAsInt();
    if (mx > 2)
      return "Three of a Kind";
    if (mx == 2)
      return "Pair";
    return "High Card";
  }

    public boolean isCorrect(int[] ranks, char[] suits, String output) {
        String expected = bestHand(ranks, suits);
        return expected.equals(output);
    }
}
