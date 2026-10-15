import java.util.*;
class Marker {
    public long minimumTime(int[] d, int[] r) {
        long d1 = d[0], d2 = d[1];
        int r1 = r[0], r2 = r[1];
        
        // Binary search on total time T
        long left = 0, right = (long) 1e18, ans = right;
        long lcm = lcm(r1, r2);
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (canFinish(mid, d1, d2, r1, r2, lcm)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    
    private boolean canFinish(long T, long d1, long d2, int r1, int r2, long lcm) {
        // Hours when drone 1 is available
        long available1 = T - T / r1;
        // Hours when drone 2 is available
        long available2 = T - T / r2;
        // Hours when both are available
        long bothAvailable = T - T / r1 - T / r2 + T / lcm;
        
        // Drone 1 can use its exclusive hours + shared hours
        // Drone 2 can use its exclusive hours + shared hours
        // Total available hours = available1 + available2 - bothAvailable = T - T/lcm
        
        // Exclusive hours for drone 1
        long exclusive1 = available1 - bothAvailable;
        // Exclusive hours for drone 2
        long exclusive2 = available2 - bothAvailable;
        
        long need1 = Math.max(0, d1 - exclusive1);
        long need2 = Math.max(0, d2 - exclusive2);
        
        return need1 + need2 <= bothAvailable;
    }
    
    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public boolean isCorrect(int[] d, int[] r, long output) {
        return minimumTime(d, r) == output;
    }
}