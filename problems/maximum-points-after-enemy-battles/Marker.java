import java.util.*;
class Marker {
  public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
    final int minEnergy = Arrays.stream(enemyEnergies).min().orElse(0);
    return currentEnergy < minEnergy
        ? 0
        : (currentEnergy + Arrays.stream(enemyEnergies).asLongStream().sum() - minEnergy) /
              minEnergy;
  }

    public boolean isCorrect(int[] enemyEnergies, int currentEnergy, long output) {
        long expected = maximumPoints(enemyEnergies, currentEnergy);
        return expected == output;
    }
}
