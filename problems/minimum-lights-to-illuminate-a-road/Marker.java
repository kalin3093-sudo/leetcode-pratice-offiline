import java.util.*;

class Marker {
    public int minLights(int[] lights) {
        int n = lights.length;
        boolean[] covered = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (lights[i] > 0) {
                int v = lights[i];
                int l = Math.max(0, i - v);
                int r = Math.min(n - 1, i + v);
                for (int j = l; j <= r; j++) {
                    covered[j] = true;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < n; ) {
            if (covered[i]) {
                i++;
            } else {
                ans++;
                int pos = Math.min(n - 1, i + 1);
                int l = Math.max(0, pos - 1);
                int r = Math.min(n - 1, pos + 1);
                for (int j = l; j <= r; j++) {
                    covered[j] = true;
                }
                i = r + 1;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] lights, int output) {
        return minLights(lights) == output;
    }
}