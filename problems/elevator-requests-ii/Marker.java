import java.util.*;
class Marker {
    public long elevatorRequests(int n, int start, int[] requests) {
        Set<Integer> set = new HashSet<>();
        for (int r : requests) set.add(r);
        List<Integer> req = new ArrayList<>(set);
        boolean startIsReq = req.contains(start);
        if (startIsReq) req.remove(Integer.valueOf(start));
        Collections.sort(req);
        
        int m = req.size();
        if (m == 0) return 0;
        
        List<Integer> all = new ArrayList<>(req);
        int insertIdx = Collections.binarySearch(all, start);
        if (insertIdx < 0) insertIdx = -insertIdx - 1;
        all.add(insertIdx, start);
        
        int sz = all.size();
        long[][][] dp = new long[sz][sz][2];
        for (long[][] row : dp) for (long[] r : row) Arrays.fill(r, Long.MAX_VALUE / 2);
        
        dp[insertIdx][insertIdx][0] = 0;
        dp[insertIdx][insertIdx][1] = 0;
        
        for (int len = 0; len < sz; len++) {
            for (int l = 0; l + len < sz; l++) {
                int r = l + len;
                if (dp[l][r][0] == Long.MAX_VALUE / 2 && dp[l][r][1] == Long.MAX_VALUE / 2) continue;
                
                int totalInRange = r - l + 1;
                boolean hasStart = (l <= insertIdx && insertIdx <= r);
                int fulfilled = totalInRange - (hasStart ? 1 : 0);
                long k = m - fulfilled;
                
                if (l > 0) {
                    long d1 = Math.abs(all.get(l) - all.get(l - 1));
                    long d2 = Math.abs(all.get(r) - all.get(l - 1));
                    dp[l - 1][r][0] = Math.min(dp[l - 1][r][0], dp[l][r][0] + k * d1);
                    dp[l - 1][r][0] = Math.min(dp[l - 1][r][0], dp[l][r][1] + k * d2);
                }
                if (r + 1 < sz) {
                    long d1 = Math.abs(all.get(r + 1) - all.get(r));
                    long d2 = Math.abs(all.get(r + 1) - all.get(l));
                    dp[l][r + 1][1] = Math.min(dp[l][r + 1][1], dp[l][r][1] + k * d1);
                    dp[l][r + 1][1] = Math.min(dp[l][r + 1][1], dp[l][r][0] + k * d2);
                }
            }
        }
        
        long ans = Long.MAX_VALUE;
        for (int l = 0; l < sz; l++) {
            for (int r = l; r < sz; r++) {
                boolean allCovered = true;
                for (int i = 0; i < sz; i++) {
                    if (i == insertIdx) continue;
                    if (i < l || i > r) { allCovered = false; break; }
                }
                if (allCovered) {
                    ans = Math.min(ans, dp[l][r][0]);
                    ans = Math.min(ans, dp[l][r][1]);
                }
            }
        }
        return ans;
    }

    public boolean isCorrect(int n, int start, int[] requests, long output) {
        return elevatorRequests(n, start, requests) == output;
    }
}