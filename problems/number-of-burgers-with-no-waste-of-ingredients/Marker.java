import java.util.*;
class Marker {
  public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
    if (tomatoSlices % 2 == 1 || tomatoSlices < 2 * cheeseSlices || tomatoSlices > cheeseSlices * 4)
      return new ArrayList<>();

    int jumboBurgers = (tomatoSlices - 2 * cheeseSlices) / 2;

    return List.of(jumboBurgers, cheeseSlices - jumboBurgers);
  }

    public boolean isCorrect(int tomatoSlices, int cheeseSlices, List<Integer> output) {
        List<Integer> expected = numOfBurgers(tomatoSlices, cheeseSlices);
        return expected.equals(output);
    }
}
