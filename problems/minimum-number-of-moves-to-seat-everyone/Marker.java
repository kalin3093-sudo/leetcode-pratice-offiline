import java.util.*;
class Marker {
  public int minMovesToSeat(int[] seats, int[] students) {
    int res = 0;

    Arrays.sort(seats);
    Arrays.sort(students);

    for (int i = 0; i < seats.length; ++i)
      res += Math.abs(seats[i] - students[i]);

    return res;
  }

    public boolean isCorrect(int[] seats, int[] students, int output) {
        int expected = minMovesToSeat(seats, students);
        return expected == output;
    }
}
