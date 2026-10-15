import java.util.*;
class Marker {
    static final int MAXV = 50000;
    int[] spf;
    Marker() {
        spf = new int[MAXV + 1];
        for (int i = 2; i <= MAXV; i++) if (spf[i] == 0) {
            for (int j = i; j <= MAXV; j += i) if (spf[j] == 0) spf[j] = i;
        }
    }
    List<Integer> getPrimeFactors(int x) {
        List<Integer> pf = new ArrayList<>();
        while (x > 1) {
            int p = spf[x];
            pf.add(p);
            while (x % p == 0) x /= p;
        }
        return pf;
    }
    public int countGoodSubseq(int[] nums, int p, int[][] queries) {
        int n = nums.length;
        int[] a = nums.clone();
        Map<Integer, List<Integer>> valToPrimes = new HashMap<>();
        valToPrimes.put(1, Collections.emptyList());
        for (int v : a) if (v % p == 0) valToPrimes.computeIfAbsent(v / p, this::getPrimeFactors);
        for (int[] q : queries) if (q[1] % p == 0) valToPrimes.computeIfAbsent(q[1] / p, this::getPrimeFactors);
        Map<Integer, Integer> primeCount = new HashMap<>();
        int active = 0;
        for (int v : a) if (v % p == 0) {
            active++;
            for (int pr : valToPrimes.get(v / p)) primeCount.merge(pr, 1, Integer::sum);
        }
        int ans = 0;
        for (int[] q : queries) {
            int idx = q[0], val = q[1];
            int old = a[idx];
            if (old % p == 0) {
                active--;
                for (int pr : valToPrimes.get(old / p)) primeCount.merge(pr, -1, Integer::sum);
            }
            a[idx] = val;
            if (val % p == 0) {
                active++;
                for (int pr : valToPrimes.get(val / p)) primeCount.merge(pr, 1, Integer::sum);
            }
            boolean ok = false;
            if (active > 0) {
                if (active < n) {
                    // Can use all active elements; need GCD of all reduced == 1
                    boolean allHaveCommonPrime = false;
                    for (int cnt : primeCount.values()) if (cnt == active) { allHaveCommonPrime = true; break; }
                    ok = !allHaveCommonPrime;
                } else {
                    // active == n, need proper subset with GCD 1
                    // Check if there's an element we can remove
                    for (int v : a) {
                        if (v % p != 0) continue;
                        int reduced = v / p;
                        boolean canRemove = true;
                        for (int pr : valToPrimes.get(reduced)) {
                            if (primeCount.getOrDefault(pr, 0) == active) { canRemove = false; break; }
                        }
                        if (canRemove) { ok = true; break; }
                    }
                }
            }
            if (ok) ans++;
        }
        return ans;
    }
    public boolean isCorrect(int[] nums, int p, int[][] queries, int output) {
        return countGoodSubseq(nums, p, queries) == output;
    }
}