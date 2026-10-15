import java.util.*;

class Marker {
    static final int MOD = 1000000007;

    public int numOfSubarrays(int[] arr) {
        int even = 1, odd = 0;
        int sum = 0;
        long res = 0;
        for (int x : arr) {
            sum += x;
            if (sum % 2 == 0) {
                res += odd;
                even++;
            } else {
                res += even;
                odd++;
            }
        }
        return (int) (res % MOD);
    }

    public boolean isCorrect(int[] arr, int output) {
        return output == numOfSubarrays(arr);
    }
}
