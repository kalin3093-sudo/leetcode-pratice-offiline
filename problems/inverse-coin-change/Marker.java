import java.util.*;
class Marker {
    public List<Integer> findCoins(int[] numWays) {
        int n = numWays.length;
        int[] ways = Arrays.copyOf(numWays, n);
        List<Integer> ans = new ArrayList<>();
        while (true) {
            int c = -1;
            for (int i = 0; i < n; i++) {
                if (ways[i] == 1) { c = i + 1; break; }
            }
            if (c == -1) break;
            ans.add(c);
            // Process right-to-left to use original values for ways[s-c]
            for (int i = n - 1; i >= c - 1; i--) {
                if (i == c - 1) ways[i] -= 1;
                else ways[i] -= ways[i - c];
                if (ways[i] < 0) return Collections.emptyList();
            }
        }
        for (int v : ways) if (v != 0) return Collections.emptyList();
        return ans;
    }

    public boolean isCorrect(int[] numWays, List<Integer> output) {
        List<Integer> expected = findCoins(numWays);
        return expected.equals(output);
    }
}