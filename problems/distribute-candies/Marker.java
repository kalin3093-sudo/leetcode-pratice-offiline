import java.util.*;
class Marker {
  public int distributeCandies(int[] candies) {
    BitSet bitset = new BitSet(200001);

    for (final int candy : candies)
      bitset.set(candy + 100000);

    return Math.min(candies.length / 2, bitset.cardinality());
  }

    public boolean isCorrect(int[] candyType, int output) {
        int expected = distributeCandies(candyType);
        return expected == output;
    }
}
