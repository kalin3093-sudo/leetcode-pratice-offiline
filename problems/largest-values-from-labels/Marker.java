import java.util.*;
class Marker {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> Integer.compare(values[b], values[a]));
        Map<Integer, Integer> used = new HashMap<>();
        int sum = 0, count = 0;
        for (int i : idx) {
            if (count >= numWanted) break;
            int lbl = labels[i];
            int u = used.getOrDefault(lbl, 0);
            if (u < useLimit) {
                used.put(lbl, u + 1);
                sum += values[i];
                count++;
            }
        }
        return sum;
    }

    public boolean isCorrect(int[] values, int[] labels, int numWanted, int useLimit, int output) {
        return output == largestValsFromLabels(values, labels, numWanted, useLimit);
    }
}