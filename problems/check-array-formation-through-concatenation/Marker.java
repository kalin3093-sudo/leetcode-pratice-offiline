import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public boolean canFormArray(int[] arr, int[][] pieces) {
    List<Integer> concatenated = new ArrayList<>();
    Map<Integer, int[]> startToPiece = new HashMap<>();

    for (int[] piece : pieces)
      startToPiece.put(piece[0], piece);

    for (final int a : arr)
      for (final int num : startToPiece.getOrDefault(a, new int[0]))
        concatenated.add(num);

    return Arrays.equals(concatenated.stream().mapToInt(Integer::intValue).toArray(), arr);
  }

    public boolean isCorrect(int[] arr, int[][] pieces, boolean output) {
        boolean expected = canFormArray(arr, pieces);
        return expected == output;
    }
}
