import java.util.*;

class Marker {
  public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
    boolean[] on = new boolean[101];
    for (int b : bulbs) on[b] = !on[b];
    List<Integer> res = new ArrayList<>();
    for (int i = 1; i <= 100; i++) {
      if (on[i]) res.add(i);
    }
    return res;
  }

  public boolean isCorrect(List<Integer> bulbs, List<Integer> output) {
    return toggleLightBulbs(bulbs).equals(output);
  }
}
