import java.util.*;
class Marker {
  public int countStudents(int[] students, int[] sandwiches) {
    int[] count = new int[2];

    for (final int student : students)
      ++count[student];

    for (int i = 0; i < sandwiches.length; ++i) {
      if (count[sandwiches[i]] == 0)
        return sandwiches.length - i;
      --count[sandwiches[i]];
    }

    return 0;
  }

    public boolean isCorrect(int[] students, int[] sandwiches, int output) {
        int expected = countStudents(students, sandwiches);
        return expected == output;
    }
}
