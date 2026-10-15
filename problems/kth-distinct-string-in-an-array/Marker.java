import java.util.*;
class Marker {
  public String kthDistinct(String[] arr, int k) {
    Map<String, Integer> count = new HashMap<>();

    for (final String a : arr)
      count.merge(a, 1, Integer::sum);

    for (final String a : arr)
      if (count.get(a) == 1 && --k == 0)
        return a;

    return "";
  }

    public boolean isCorrect(String[] arr, int k, String output) {
        String expected = kthDistinct(arr, k);
        return expected.equals(output);
    }
}
