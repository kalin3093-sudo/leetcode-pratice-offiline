import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.math.BigInteger;

class Marker {
  // Same as 3180. Maximum Total Reward Using Operations I
  public int maxTotalReward(int[] rewardValues) {
    BigInteger one = BigInteger.ONE;
    BigInteger dp = one; // the possible rewards (initially, 0 is achievable)

    Arrays.sort(rewardValues);

    for (final int num : rewardValues) {
      // Remove the numbers >= the current number.
      BigInteger maskBitsLessThanNum = one.shiftLeft(num).subtract(one);
      BigInteger bitsLessThanNum = dp.and(maskBitsLessThanNum);
      dp = dp.or(bitsLessThanNum.shiftLeft(num));
    }

    return dp.bitLength() - 1;
  }

    public boolean isCorrect(int[] rewardValues, int output) {
        int expected = maxTotalReward(rewardValues);
        return expected == output;
    }
}
