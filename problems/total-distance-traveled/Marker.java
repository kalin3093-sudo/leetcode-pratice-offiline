import java.util.*;
class Marker {
  public int distanceTraveled(int mainTank, int additionalTank) {
    // M  M M M M  A  M M M M   A
    // 1 [2 3 4 5] 6 [7 8 9 10] 11
    return (mainTank + Math.min((mainTank - 1) / 4, additionalTank)) * 10;
  }

    public boolean isCorrect(int mainTank, int additionalTank, int output) {
        int expected = distanceTraveled(mainTank, additionalTank);
        return expected == output;
    }
}
