import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  // You need treat n as an unsigned value
  public int reverseBits(int n) {
    int ans = 0;

    for (int i = 0; i < 32; ++i)
      if ((n >> i & 1) == 1)
        ans |= 1 << 31 - i;

    return ans;
  }

    public boolean isCorrect(int n, int output) {
        int expected = reverseBits(n);
        return expected == output;
    }
}
