import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            children[prevRoom[i]].add(i);
        }

        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % MOD;
        invFact[n] = modPow(fact[n], MOD - 2);
        for (int i = n; i > 0; i--) invFact[i - 1] = invFact[i] * i % MOD;

        long[] dp = new long[n];
        int[] size = new int[n];
        dfs(0, children, dp, size, fact, invFact);
        return (int) dp[0];
    }

    private void dfs(int u, List<Integer>[] children, long[] dp, int[] size, long[] fact, long[] invFact) {
        size[u] = 1;
        dp[u] = 1;
        for (int v : children[u]) {
            dfs(v, children, dp, size, fact, invFact);
            size[u] += size[v];
            dp[u] = dp[u] * dp[v] % MOD;
        }
        // ways to interleave children subtrees
        long ways = fact[size[u] - 1];
        for (int v : children[u]) {
            ways = ways * invFact[size[v]] % MOD;
        }
        dp[u] = dp[u] * ways % MOD;
    }

    private long modPow(long a, long e) {
        long res = 1;
        while (e > 0) {
            if ((e & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            e >>= 1;
        }
        return res;
    }

    public boolean isCorrect(int[] prevRoom, int output) {
        return output == waysToBuildRooms(prevRoom);
    }
}