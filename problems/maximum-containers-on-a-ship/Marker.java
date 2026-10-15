import java.util.*;
class Marker {
  public int maxContainers(int n, int w, int maxWeight) {
    return Math.min(n * n, maxWeight / w);
  }

    public boolean isCorrect(int n, int w, int maxWeight, int output) {
        int expected = maxContainers(n, w, maxWeight);
        return expected == output;
    }
}
