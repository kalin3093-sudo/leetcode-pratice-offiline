import java.util.*;
class Marker {
    public boolean completePrime(int num) {
        String s = String.valueOf(num);
        for (int i = 1; i <= s.length(); i++) {
            int pre = Integer.parseInt(s.substring(0, i));
            int suf = Integer.parseInt(s.substring(s.length() - i));
            if (!isPrime(pre) || !isPrime(suf)) return false;
        }
        return true;
    }

    private static boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; (long) i * i <= x; i++) if (x % i == 0) return false;
        return true;
    }

    public boolean isCorrect(int num, boolean output) {
        return completePrime(num) == output;
    }
}
