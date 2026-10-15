import java.util.*;
class Marker {
    public long maxRatings(int[][] units) {
        int n = units.length;
        int[] min1 = new int[n];
        int[] min2 = new int[n];
        long initialSum = 0;
        
        for (int i = 0; i < n; i++) {
            Arrays.sort(units[i]);
            min1[i] = units[i][0];
            min2[i] = units[i].length > 1 ? units[i][1] : 0;
            initialSum += min1[i];
        }
        
        long ans = initialSum;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                
                int newIRating = units[i].length > 1 ? min2[i] : 0;
                int newJRating = Math.min(min1[j], min1[i]);
                
                long curr = initialSum - min1[i] - min1[j] + newIRating + newJRating;
                ans = Math.max(ans, curr);
            }
        }
        
        return ans;
    }

    public boolean isCorrect(int[][] units, long output) {
        return maxRatings(units) == output;
    }
}