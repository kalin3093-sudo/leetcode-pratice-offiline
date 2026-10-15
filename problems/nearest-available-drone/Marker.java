import java.util.*;
class Marker {
    public int nearestDrone(int[][] drones, int[] target) {
        int tx = target[0], ty = target[1];
        int bestIdx = -1;
        int bestDist = Integer.MAX_VALUE;
        
        for (int i = 0; i < drones.length; i++) {
            int dx = drones[i][0], dy = drones[i][1], range = drones[i][2];
            int dist = Math.abs(dx - tx) + Math.abs(dy - ty);
            if (dist <= range) {
                if (dist < bestDist) {
                    bestDist = dist;
                    bestIdx = i;
                }
            }
        }
        
        return bestIdx;
    }

    public boolean isCorrect(int[][] drones, int[] target, int output) {
        return nearestDrone(drones, target) == output;
    }
}