import java.util.*;

class Marker {
    public int[] countTasks(int[] tasks, int[] shifts) {
        int n = tasks.length;
        long[] prefix = new long[n];
        prefix[0] = tasks[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + tasks[i];
        }
        long total = prefix[n - 1];

        int[] ans = new int[shifts.length];
        long done = 0;
        for (int j = 0; j < shifts.length; j++) {
            done += shifts[j];
            if (done >= total) {
                ans[j] = 0;
                done = 0;
            } else {
                int completed = upperBound(prefix, done);
                ans[j] = n - completed;
            }
        }
        return ans;
    }

    private int upperBound(long[] prefix, long val) {
        int lo = 0, hi = prefix.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (prefix[mid] <= val) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public boolean isCorrect(int[] tasks, int[] shifts, int[] output) {
        int[] expected = countTasks(tasks, shifts);
        return Arrays.equals(expected, output);
    }
}