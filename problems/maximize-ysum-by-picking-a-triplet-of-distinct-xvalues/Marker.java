import java.util.*;
class Marker {
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        Map<Integer, Integer> maxY = new HashMap<>();
        for (int i = 0; i < x.length; i++) {
            maxY.put(x[i], Math.max(maxY.getOrDefault(x[i], Integer.MIN_VALUE), y[i]));
        }
        
        if (maxY.size() < 3) return -1;
        
        List<Integer> values = new ArrayList<>(maxY.values());
        values.sort(Collections.reverseOrder());
        
        return values.get(0) + values.get(1) + values.get(2);
    }

    public boolean isCorrect(int[] x, int[] y, int output) {
        return maxSumDistinctTriplet(x, y) == output;
    }
}