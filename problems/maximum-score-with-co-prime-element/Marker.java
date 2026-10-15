import java.util.*;
class Marker {
    public int maxScore(int[] nums, int maxVal) {
        int MAX = 100000;
        int[] freq = new int[MAX + 1];
        for (int x : nums) freq[x]++;

        int[] cnt = new int[MAX + 1];
        for (int d = 1; d <= MAX; d++) {
            for (int m = d; m <= MAX; m += d) {
                cnt[d] += freq[m];
            }
        }

        int[] sp = new int[MAX + 1];
        for (int i = 2; i <= MAX; i++) {
            if (sp[i] == 0) {
                for (int j = i; j <= MAX; j += i) {
                    if (sp[j] == 0) sp[j] = i;
                }
            }
        }

        int best = Integer.MIN_VALUE;
        Set<Integer> candidates = new HashSet<>();
        for (int x : nums) candidates.add(x);
        for (int x = 1; x <= maxVal; x++) candidates.add(x);

        for (int x : candidates) {
            if (x > MAX) continue;
            int[] primes = getPrimeFactors(x, sp);
            int notCoprime = countNotCoprime(primes, cnt);
            
            int cost;
            if (freq[x] > 0) {
                cost = notCoprime - (x > 1 ? 1 : 0);
            } else {
                cost = notCoprime > 0 ? notCoprime : 1;
            }
            int score = x - cost;
            best = Math.max(best, score);
        }
        return best;
    }

    private int[] getPrimeFactors(int x, int[] sp) {
        List<Integer> primes = new ArrayList<>();
        while (x > 1) {
            int p = sp[x];
            primes.add(p);
            while (x % p == 0) x /= p;
        }
        return primes.stream().mapToInt(Integer::intValue).toArray();
    }

    private int countNotCoprime(int[] primes, int[] cnt) {
        int m = primes.length;
        int total = 0;
        for (int mask = 1; mask < (1 << m); mask++) {
            int prod = 1;
            int bits = 0;
            for (int i = 0; i < m; i++) {
                if ((mask & (1 << i)) != 0) {
                    prod *= primes[i];
                    bits++;
                }
            }
            if (prod > 100000) continue;
            if (bits % 2 == 1) total += cnt[prod];
            else total -= cnt[prod];
        }
        return total;
    }

    public boolean isCorrect(int[] nums, int maxVal, int output) {
        int expected = maxScore(nums, maxVal);
        return expected == output;
    }
}