import java.util.*;

class Marker {
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        int n = costs.length;
        int[][] machines = new int[n][2];
        for (int i = 0; i < n; i++) {
            machines[i][0] = costs[i];
            machines[i][1] = capacity[i];
        }
        Arrays.sort(machines, Comparator.comparingInt(a -> a[0]));
        int[] prefMax = new int[n];
        prefMax[0] = machines[0][1];
        for (int i = 1; i < n; i++) {
            prefMax[i] = Math.max(prefMax[i - 1], machines[i][1]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (machines[i][0] < budget) {
                ans = Math.max(ans, machines[i][1]);
            }
            int remaining = budget - machines[i][0] - 1;
            if (remaining < machines[0][0]) continue;
            int lo = 0, hi = i - 1, j = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (machines[mid][0] <= remaining) {
                    j = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            if (j != -1) {
                ans = Math.max(ans, machines[i][1] + prefMax[j]);
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] costs, int[] capacity, int budget, int output) {
        return output == maxCapacity(costs, capacity, budget);
    }
}