public class Marker {

    public int countBinaryPalindromes(long n) {
        if (n == 0) return 1;
        int len = Long.toBinaryString(n).length();
        int count = 1;
        for (int l = 1; l < len; l++) {
            if (l == 1) count += 1;
            else count += 1 << ((l - 1) / 2);
        }
        int half = (len + 1) / 2;
        long prefix = n >> (len - half);
        long pal = makePalindrome(prefix, len);
        if (pal <= n) count++;
        return count;
    }

    long makePalindrome(long halfBits, int len) {
        long res = halfBits;
        long t = halfBits;
        if (len % 2 == 1) t /= 2;
        while (t > 0) {
            res = res * 2 + (t % 2);
            t /= 2;
        }
        return res;
    }

    public boolean isCorrect(long n, int out) {
        return countBinaryPalindromes(n) == out;
    }
}
