import java.util.*;

class Marker {
    public int minGroupsForValidAssignment(int[] balls) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int b : balls) {
            freq.put(b, freq.getOrDefault(b, 0) + 1);
        }
        
        int minFreq = Integer.MAX_VALUE;
        for (int f : freq.values()) {
            minFreq = Math.min(minFreq, f);
        }
        
        int ans = Integer.MAX_VALUE;
        for (int x = 1; x <= minFreq; x++) {
            int groups = 0;
            boolean valid = true;
            for (int f : freq.values()) {
                int a = f / (x + 1);
                int b = f % (x + 1);
                if (b == 0) {
                    groups += a;
                } else if (x - b <= a) {
                    groups += a + 1;
                } else {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans = Math.min(ans, groups);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public boolean isCorrect(int[] balls, int output) {
        return minGroupsForValidAssignment(balls) == output;
    }
}