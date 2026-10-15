import java.util.*;
class Marker {
  public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
    if (k <= numOnes)
      return k;
    if (k <= numOnes + numZeros)
      return numOnes;
    return numOnes - (k - numOnes - numZeros);
  }

    public boolean isCorrect(int numOnes, int numZeros, int numNegOnes, int k, int output) {
        int expected = kItemsWithMaximumSum(numOnes, numZeros, numNegOnes, k);
        return expected == output;
    }
}
