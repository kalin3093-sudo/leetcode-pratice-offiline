import java.util.*;
class Marker {
  public boolean canPlaceFlowers(int[] flowerbed, int n) {
    if (n == 0)
      return true;

    for (int i = 0; i < flowerbed.length; ++i)
      if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) &&
          (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
        flowerbed[i] = 1;
        if (--n == 0)
          return true;
      }

    return false;
  }

    public boolean isCorrect(int[] flowerbed, int n, boolean output) {
        boolean expected = canPlaceFlowers(flowerbed, n);
        return expected == output;
    }
}
