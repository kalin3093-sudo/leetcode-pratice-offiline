import java.util.*;
class Marker {
  public int maximumSwap(int num) {
    char[] s = Integer.toString(num).toCharArray();
    int[] lastIndex = new int[10]; // {digit: last index}

    for (int i = 0; i < s.length; ++i)
      lastIndex[s[i] - '0'] = i;

    for (int i = 0; i < s.length; ++i)
      for (int d = 9; d > s[i] - '0'; --d)
        if (lastIndex[d] > i) {
          s[lastIndex[d]] = s[i];
          s[i] = (char) ('0' + d);
          return Integer.parseInt(new String(s));
        }

    return num;
  }

    public boolean isCorrect(int num, int output) {
        int expected = maximumSwap(num);
        return expected == output;
    }
}
