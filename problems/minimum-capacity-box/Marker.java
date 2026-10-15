import java.util.*;
class Marker {
    public int minimumIndex(int[] capacity, int itemSize) {
        int minCap = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < capacity.length; i++) {
            if (capacity[i] >= itemSize && capacity[i] < minCap) {
                minCap = capacity[i];
                ans = i;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] capacity, int itemSize, int output) {
        int expected = minimumIndex(capacity, itemSize);
        return expected == output;
    }
}