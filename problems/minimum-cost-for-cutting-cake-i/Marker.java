import java.util.*;
class Marker {
  public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
    int cost = 0;
    int sumH = Arrays.stream(horizontalCut).sum();
    int sumV = Arrays.stream(verticalCut).sum();

    Arrays.sort(horizontalCut);
    Arrays.sort(verticalCut);

    for (int i = m - 2, j = n - 2; i >= 0 && j >= 0;)
      if (horizontalCut[i] > verticalCut[j]) {
        cost += horizontalCut[i] + sumV;
        sumH -= horizontalCut[i--];
      } else {
        cost += verticalCut[j] + sumH;
        sumV -= verticalCut[j--];
      }

    return cost + sumH + sumV;
  }

    public boolean isCorrect(int m, int n, int[] horizontalCut, int[] verticalCut, int output) {
        int expected = minimumCost(m, n, horizontalCut, verticalCut);
        return expected == output;
    }
}
