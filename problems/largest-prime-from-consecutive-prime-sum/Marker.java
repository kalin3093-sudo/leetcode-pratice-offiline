import java.util.*;
class Marker {
    public int largestPrime(int n) {
        boolean[] isPrime = sieve(n);
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) if (isPrime[i]) primes.add(i);
        int best = 0;
        long sum = 0;
        for (int p : primes) {
            sum += p;
            if (sum > n) break;
            if (isPrime[(int) sum]) best = (int) sum;
        }
        return best;
    }

    private boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) isPrime[j] = false;
            }
        }
        return isPrime;
    }

    public boolean isCorrect(int n, int output) {
        return output == largestPrime(n);
    }
}