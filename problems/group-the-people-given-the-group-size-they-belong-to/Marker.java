import java.util.*;
class Marker {
  public List<List<Integer>> groupThePeople(int[] groupSizes) {
    List<List<Integer>> ans = new ArrayList<>();
    Map<Integer, List<Integer>> groupSizeToIndices = new HashMap<>();

    for (int i = 0; i < groupSizes.length; ++i) {
      final int groupSize = groupSizes[i];
      groupSizeToIndices.putIfAbsent(groupSize, new ArrayList<>());
      groupSizeToIndices.get(groupSize).add(i);
    }

    for (Map.Entry<Integer, List<Integer>> entry : groupSizeToIndices.entrySet()) {
      final int groupSize = entry.getKey();
      List<Integer> indices = entry.getValue();
      List<Integer> groupIndices = new ArrayList<>();
      for (final int index : indices) {
        groupIndices.add(index);
        if (groupIndices.size() == groupSize) {
          ans.add(new ArrayList<>(groupIndices));
          groupIndices.clear();
        }
      }
    }

    return ans;
  }

    public boolean isCorrect(int[] groupSizes, List<List<Integer>> output) {
        List<List<Integer>> expected = groupThePeople(groupSizes);
        return expected.equals(output);
    }
}
