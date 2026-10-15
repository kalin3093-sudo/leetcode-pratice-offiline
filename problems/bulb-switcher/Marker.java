import java.util.*;
class Marker {
  public int bulbSwitch(int n) {
    // The k-th bulb can only be switched when k % i == 0.
    // So, we can rephrase the problem:
    // To find number of numbers <= n that have odd factors.
    // Obviously, only square numbers have odd factor(s).
    // e.g. n = 10, only 1, 4, and 9 are square numbers that <= 10
    return (int) Math.sqrt(n);
  }

    public boolean isCorrect(int n, int output) {
        int expected = bulbSwitch(n);
        return expected == output;
    }
}
