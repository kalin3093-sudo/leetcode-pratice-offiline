import java.util.*;
class Marker {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length, m = waterStartTime.length;
        
        Integer[] wIdx = new Integer[m];
        for (int i = 0; i < m; i++) wIdx[i] = i;
        Arrays.sort(wIdx, Comparator.comparingInt(i -> waterStartTime[i]));
        
        int[] wPrefMin = new int[m];
        int[] wSufMin = new int[m];
        for (int i = 0; i < m; i++) {
            int idx = wIdx[i];
            wPrefMin[i] = (i == 0) ? waterDuration[idx] : Math.min(wPrefMin[i - 1], waterDuration[idx]);
            wSufMin[i] = waterStartTime[idx] + waterDuration[idx];
        }
        for (int i = m - 2; i >= 0; i--) {
            wSufMin[i] = Math.min(wSufMin[i], wSufMin[i + 1]);
        }
        
        Integer[] lIdx = new Integer[n];
        for (int i = 0; i < n; i++) lIdx[i] = i;
        Arrays.sort(lIdx, Comparator.comparingInt(i -> landStartTime[i]));
        
        int[] lPrefMin = new int[n];
        int[] lSufMin = new int[n];
        for (int i = 0; i < n; i++) {
            int idx = lIdx[i];
            lPrefMin[i] = (i == 0) ? landDuration[idx] : Math.min(lPrefMin[i - 1], landDuration[idx]);
            lSufMin[i] = landStartTime[idx] + landDuration[idx];
        }
        for (int i = n - 2; i >= 0; i--) {
            lSufMin[i] = Math.min(lSufMin[i], lSufMin[i + 1]);
        }
        
        int ans = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int finish1 = landStartTime[i] + landDuration[i];
            int pos = upperBound(wIdx, waterStartTime, finish1);
            int cand = Integer.MAX_VALUE;
            if (pos > 0) cand = Math.min(cand, finish1 + wPrefMin[pos - 1]);
            if (pos < m) cand = Math.min(cand, wSufMin[pos]);
            ans = Math.min(ans, cand);
        }
        
        for (int i = 0; i < m; i++) {
            int finish1 = waterStartTime[i] + waterDuration[i];
            int pos = upperBound(lIdx, landStartTime, finish1);
            int cand = Integer.MAX_VALUE;
            if (pos > 0) cand = Math.min(cand, finish1 + lPrefMin[pos - 1]);
            if (pos < n) cand = Math.min(cand, lSufMin[pos]);
            ans = Math.min(ans, cand);
        }
        return ans;
    }
    
    private int upperBound(Integer[] idx, int[] arr, int target) {
        int lo = 0, hi = idx.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[idx[mid]] <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public boolean isCorrect(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration, int output) {
        return earliestFinishTime(landStartTime, landDuration, waterStartTime, waterDuration) == output;
    }
}