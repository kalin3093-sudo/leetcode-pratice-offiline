import java.util.*;
class Marker {
  public int accountBalanceAfterPurchase(int purchaseAmount) {
    return 100 - ((purchaseAmount + 5) / 10) * 10;
  }

    public boolean isCorrect(int purchaseAmount, int output) {
        int expected = accountBalanceAfterPurchase(purchaseAmount);
        return expected == output;
    }
}
