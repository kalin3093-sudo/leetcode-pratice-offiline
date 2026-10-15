import java.util.*;
class Marker {
    public int minOperations(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        String t = new String(arr);
        if (s.equals(t)) return 0;
        int n = s.length();
        if (n == 2) return -1;
        char mn = t.charAt(0), mx = t.charAt(n - 1);
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) return 1;
        boolean mnPrefix = false;
        for (int i = 0; i < n - 1; i++) if (s.charAt(i) == mn) mnPrefix = true;
        boolean mxSuffix = false;
        for (int i = 1; i < n; i++) if (s.charAt(i) == mx) mxSuffix = true;
        if (mnPrefix || mxSuffix) return 2;
        return 3;
    }

    public boolean isCorrect(String s, int output) {
        return output == minOperations(s);
    }
}
