import java.util.*;

class Marker {
    static long CAP;

    static long binomCap(int n, int r) {
        r = Math.min(r, n - r);
        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - r + i) / i;
            if (res >= CAP)
                return CAP;
        }
        return res;
    }

    static long permCount(int[] cnt) {
        long res = 1;
        int sum = 0;
        for (int c : cnt) {
            if (c == 0)
                continue;
            sum += c;
            res *= binomCap(sum, c);
            if (res >= CAP)
                return CAP;
        }
        return res;
    }

    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray())
            cnt[c - 'a']++;
        int[] halfCnt = new int[26];
        char mid = 0;
        int totalHalf = 0;
        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
            totalHalf += halfCnt[i];
            if (cnt[i] % 2 == 1)
                mid = (char) ('a' + i);
        }
        CAP = (long) k + 1;
        if (permCount(halfCnt) < k)
            return "";
        StringBuilder half = new StringBuilder();
        int remaining = totalHalf;
        for (int pos = 0; pos < totalHalf; pos++) {
            for (int c = 0; c < 26; c++) {
                if (halfCnt[c] == 0)
                    continue;
                halfCnt[c]--;
                long perms = permCount(halfCnt);
                if (perms >= k) {
                    half.append((char) ('a' + c));
                    break;
                }
                k -= (int) perms;
                halfCnt[c]++;
            }
            remaining--;
        }
        StringBuilder res = new StringBuilder(half);
        if (mid != 0)
            res.append(mid);
        res.append(half.reverse());
        return res.toString();
    }

    public boolean isCorrect(String s, int k, String output) {
        return smallestPalindrome(s, k).equals(output);
    }
}
