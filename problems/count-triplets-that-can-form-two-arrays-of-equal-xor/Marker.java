import java.util.*;

class Marker {
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] ^ arr[i];
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                if (pref[i] == pref[k + 1]) {
                    ans += k - i;
                }
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] arr, int output) {
        return output == countTriplets(arr);
    }
}