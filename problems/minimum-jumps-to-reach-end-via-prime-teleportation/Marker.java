import java.util.*;

class Marker {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int maxVal = 1000000;
        int[] spf = new int[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            if (spf[i] == 0) {
                spf[i] = i;
                if ((long) i * i <= maxVal) {
                    for (int j = i * i; j <= maxVal; j += i) {
                        if (spf[j] == 0) spf[j] = i;
                    }
                }
            }
        }

        List<Integer>[] primeBuckets = new List[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            primeBuckets[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int p = 2; p * p <= x; p++) {
                if (x % p == 0) {
                    if (spf[p] == p) {
                        primeBuckets[p].add(i);
                    }
                    while (x % p == 0) x /= p;
                }
            }
            if (x > 1 && spf[x] == x) {
                primeBuckets[x].add(i);
            }
        }

        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Deque<Integer> q = new ArrayDeque<>();
        dist[0] = 0;
        q.add(0);

        boolean[] bucketUsed = new boolean[maxVal + 1];

        while (!q.isEmpty()) {
            int i = q.poll();
            if (i == n - 1) return dist[i];

            int d = dist[i] + 1;
            if (i - 1 >= 0 && dist[i - 1] == -1) {
                dist[i - 1] = d;
                q.add(i - 1);
            }
            if (i + 1 < n && dist[i + 1] == -1) {
                dist[i + 1] = d;
                q.add(i + 1);
            }

            int x = nums[i];
            if (x < 2 || spf[x] != x) continue;
            int p = x;
            if (bucketUsed[p]) continue;
            bucketUsed[p] = true;
            for (int j : primeBuckets[p]) {
                if (dist[j] == -1) {
                    dist[j] = d;
                    q.add(j);
                }
            }
        }
        return -1;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minJumps(nums) == output;
    }
}