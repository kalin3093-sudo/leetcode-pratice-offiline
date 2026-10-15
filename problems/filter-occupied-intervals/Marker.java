import java.util.*;
class Marker {
    public List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        Arrays.sort(occupiedIntervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] iv : occupiedIntervals) {
            if (merged.isEmpty() || iv[0] > merged.get(merged.size() - 1)[1] + 1) {
                merged.add(new int[]{iv[0], iv[1]});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], iv[1]);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] iv : merged) {
            int s = iv[0], e = iv[1];
            if (e < freeStart || s > freeEnd) {
                ans.add(Arrays.asList(s, e));
            } else {
                if (s < freeStart) ans.add(Arrays.asList(s, freeStart - 1));
                if (e > freeEnd) ans.add(Arrays.asList(freeEnd + 1, e));
            }
        }
        return ans;
    }

    public boolean isCorrect(int[][] occupiedIntervals, int freeStart, int freeEnd, List<List<Integer>> output) {
        List<List<Integer>> expected = filterOccupiedIntervals(occupiedIntervals, freeStart, freeEnd);
        if (output == null || output.size() != expected.size()) return false;
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(output.get(i))) return false;
        }
        return true;
    }
}