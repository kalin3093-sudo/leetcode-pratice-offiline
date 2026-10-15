import java.util.*;
class Marker {
    public int elevatorRequests(int n, int[] requests) {
        int curr = 0, ans = 0;
        for (int r : requests) {
            ans += Math.abs(r - curr);
            curr = r;
        }
        return ans;
    }

    public boolean isCorrect(int n, int[] requests, int output) {
        return elevatorRequests(n, requests) == output;
    }
}