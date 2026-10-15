import java.util.*;
class Marker {
    public int[][] generateSchedule(int n) {
        if (n == 3) return new int[0][0];
        int total = n * (n - 1);
        int[][] matches = new int[total][2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) matches[idx++] = new int[]{i, j};
            }
        }
        List<int[]> schedule = new ArrayList<>();
        int[] lastPlayed = new int[n];
        Arrays.fill(lastPlayed, -2);
        int[] remaining = new int[n];
        Arrays.fill(remaining, 2 * (n - 1));
        boolean[] used = new boolean[total];
        for (int day = 0; day < total; day++) {
            int bestIdx = -1, bestScore = -1;
            for (int i = 0; i < total; i++) {
                if (used[i]) continue;
                int h = matches[i][0], a = matches[i][1];
                if (lastPlayed[h] == day - 1 || lastPlayed[a] == day - 1) continue;
                int score = remaining[h] + remaining[a];
                if (score > bestScore) {
                    bestScore = score;
                    bestIdx = i;
                }
            }
            if (bestIdx == -1) {
                for (int i = 0; i < total; i++) {
                    if (used[i]) continue;
                    int h = matches[i][0], a = matches[i][1];
                    if (lastPlayed[h] != day - 1 && lastPlayed[a] != day - 1) {
                        bestIdx = i;
                        break;
                    }
                }
                if (bestIdx == -1) return new int[0][0];
            }
            used[bestIdx] = true;
            int h = matches[bestIdx][0], a = matches[bestIdx][1];
            schedule.add(new int[]{h, a});
            lastPlayed[h] = lastPlayed[a] = day;
            remaining[h]--; remaining[a]--;
        }
        return schedule.toArray(new int[0][]);
    }
    public boolean isCorrect(int n, int[][] output) {
        int[][] expected = generateSchedule(n);
        if (expected.length == 0 && output.length == 0) return true;
        if (expected.length != output.length) return false;
        Set<String> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (int[] m : expected) set1.add(m[0] + "," + m[1]);
        for (int[] m : output) set2.add(m[0] + "," + m[1]);
        return set1.equals(set2);
    }
}