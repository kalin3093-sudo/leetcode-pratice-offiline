import java.util.*;
class Marker {
  public int minimumRefill(int[] plants, int capacityA, int capacityB) {
    int ans = 0;
    int i = 0;
    int j = plants.length - 1;
    int canA = capacityA;
    int canB = capacityB;

    while (i < j) {
      ans += (canA < plants[i] ? 1 : 0) + (canB < plants[j] ? 1 : 0);
      if (canA < plants[i])
        canA = capacityA;
      if (canB < plants[j])
        canB = capacityB;
      canA -= plants[i++];
      canB -= plants[j--];
    }

    return ans + (i == j && Math.max(canA, canB) < plants[i] ? 1 : 0);
  }

    public boolean isCorrect(int[] plants, int capacityA, int capacityB, int output) {
        int expected = minimumRefill(plants, capacityA, capacityB);
        return expected == output;
    }
}
