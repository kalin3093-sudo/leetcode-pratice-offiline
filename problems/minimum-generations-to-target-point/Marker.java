import java.util.*;
class Marker {
    public int minGenerations(int[][] points, int[] target) {
        int tx = target[0], ty = target[1], tz = target[2];
        Set<Long> have = new HashSet<>();
        for (int[] p : points) have.add(enc(p[0], p[1], p[2]));
        long t = enc(tx, ty, tz);
        if (have.contains(t)) return 0;
        int gen = 0;
        while (true) {
            List<Long> list = new ArrayList<>(have);
            Set<Long> fresh = new HashSet<>();
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    long a = list.get(i), b = list.get(j);
                    int x = (int)((a / 49 + b / 49) / 2);
                    int y = (int)(((a / 7 % 7) + (b / 7 % 7)) / 2);
                    int z = (int)((a % 7 + b % 7) / 2);
                    long c = enc(x, y, z);
                    if (c != a && c != b && !have.contains(c)) fresh.add(c);
                }
            }
            if (fresh.isEmpty()) return -1;
            gen++;
            if (fresh.contains(t)) return gen;
            have.addAll(fresh);
        }
    }

    private long enc(int x, int y, int z) {
        return x * 49L + y * 7L + z;
    }

    public boolean isCorrect(int[][] points, int[] target, int output) {
        int expected = minGenerations(points, target);
        return expected == output;
    }
}
