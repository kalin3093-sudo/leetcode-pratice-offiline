import java.util.*;
class Marker {
    public long maxPoints(int[] technique1, int[] technique2, int k) {
        int n = technique1.length;
        long sum1 = 0;
        for (int x : technique1) sum1 += x;
        
        Integer[] deltas = new Integer[n];
        for (int i = 0; i < n; i++) {
            deltas[i] = technique2[i] - technique1[i];
        }
        Arrays.sort(deltas, Collections.reverseOrder());
        
        long ans = sum1;
        long curr = sum1;
        for (int i = 0; i < n - k; i++) {
            curr += deltas[i];
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public boolean isCorrect(int[] technique1, int[] technique2, int k, long output) {
        return maxPoints(technique1, technique2, k) == output;
    }
}