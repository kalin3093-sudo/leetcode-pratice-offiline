import java.util.*;

class Marker {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] a = new int[n][3];
        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
        }
        
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, Comparator.comparingInt(i -> a[i][1]));
        
        int[] L = new int[n], R = new int[n], W = new int[n];
        for (int i = 0; i < n; i++) {
            L[i] = a[idx[i]][0];
            R[i] = a[idx[i]][1];
            W[i] = a[idx[i]][2];
        }
        
        int[][] dp = new int[5][n];
        boolean[][] take = new boolean[5][n];
        int[][] prevI = new int[5][n];
        int[][] prevR = new int[5][n];
        
        for (int r = 1; r <= 4; r++) {
            for (int i = 0; i < n; i++) {
                int bestScore = (i > 0 ? dp[r][i - 1] : 0);
                boolean bestTake = false;
                int bestPrevI = i - 1;
                int bestPrevR = r;
                
                int lo = 0, hi = i - 1, j = -1;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;
                    if (R[mid] < L[i]) {
                        j = mid;
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
                int score = W[i] + (j >= 0 ? dp[r - 1][j] : 0);
                if (score > bestScore) {
                    bestScore = score;
                    bestTake = true;
                    bestPrevI = j;
                    bestPrevR = r - 1;
                }
                
                dp[r][i] = bestScore;
                take[r][i] = bestTake;
                prevI[r][i] = bestPrevI;
                prevR[r][i] = bestPrevR;
            }
        }
        
        int bestR = 0, bestI = n - 1;
        for (int r = 1; r <= 4; r++) {
            if (dp[r][n - 1] > dp[bestR][bestI]) {
                bestR = r;
                bestI = n - 1;
            }
        }
        
        List<Integer> chosen = new ArrayList<>();
        int r = bestR, i = bestI;
        while (r > 0 && i >= 0) {
            if (take[r][i]) {
                chosen.add(idx[i]);
                r = prevR[r][i];
                i = prevI[r][i];
            } else {
                i--;
            }
        }
        Collections.sort(chosen);
        int[] ans = new int[chosen.size()];
        for (int k = 0; k < chosen.size(); k++) ans[k] = chosen.get(k);
        return ans;
    }

    public boolean isCorrect(List<List<Integer>> intervals, int[] output) {
        int[] expected = maximumWeight(intervals);
        if (output.length != expected.length) return false;
        for (int i = 0; i < output.length; i++) {
            if (output[i] != expected[i]) return false;
        }
        return true;
    }
}