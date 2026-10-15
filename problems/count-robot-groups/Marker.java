import java.util.*;

class Marker {
    public int countGroups(int[] position, int[] speed, int distance) {
        int n = position.length;
        if (n == 0) return 0;

        List<Integer> groupPos = new ArrayList<>();
        List<Integer> groupSpeed = new ArrayList<>();

        int i = 0;
        while (i < n) {
            int j = i;
            while (j + 1 < n && position[j + 1] - position[j] <= distance) {
                j++;
            }
            groupPos.add(position[j]);
            groupSpeed.add(speed[j]);
            i = j + 1;
        }

        int groups = groupPos.size();
        int rightmostIdx = groups - 1;
        for (int g = groups - 2; g >= 0; g--) {
            if (groupSpeed.get(g) > groupSpeed.get(rightmostIdx)) {
            } else {
                rightmostIdx = g;
            }
        }
        return rightmostIdx + 1;
    }

    public boolean isCorrect(int[] position, int[] speed, int distance, int output) {
        return output == countGroups(position, speed, distance);
    }
}