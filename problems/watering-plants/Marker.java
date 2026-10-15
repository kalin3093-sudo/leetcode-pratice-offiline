import java.util.*;

class Marker {
    public int wateringPlants(int[] plants, int capacity) {
        int steps = 0;
        int cur = capacity;
        for (int i = 0; i < plants.length; i++) {
            if (cur >= plants[i]) {
                cur -= plants[i];
                steps++;
            } else {
                steps += i + i + 1;
                cur = capacity - plants[i];
            }
        }
        return steps;
    }

    public boolean isCorrect(int[] plants, int capacity, int output) {
        return output == wateringPlants(plants, capacity);
    }
}
