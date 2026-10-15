import java.util.*;
class Marker {
  public List<Integer> selfDividingNumbers(int left, int right) {
    List<Integer> ans = new ArrayList<>();

    for (int num = left; num <= right; ++num)
      if (dividingNumber(num))
        ans.add(num);

    return ans;
  }

  private boolean dividingNumber(int num) {
    for (int n = num; n > 0; n /= 10)
      if (n % 10 == 0 || num % (n % 10) != 0)
        return false;
    return true;
  }

    public boolean isCorrect(int left, int right, List<Integer> output) {
        List<Integer> expected = selfDividingNumbers(left, right);
        return expected.equals(output);
    }
}
