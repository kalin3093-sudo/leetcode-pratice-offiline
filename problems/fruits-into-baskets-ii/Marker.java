import java.util.*;
class Marker {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] used = new boolean[n];
        int unplaced = 0;
        for (int f : fruits) {
            int idx = -1;
            for (int j = 0; j < n; j++) {
                if (!used[j] && baskets[j] >= f) {
                    idx = j;
                    break;
                }
            }
            if (idx == -1) unplaced++;
            else used[idx] = true;
        }
        return unplaced;
    }
    public boolean isCorrect(int[] fruits, int[] baskets, int output) {
        return numOfUnplacedFruits(fruits, baskets) == output;
    }
}