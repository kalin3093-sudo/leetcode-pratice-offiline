import java.util.*;
class Marker {
  public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
    if (ruleKey.equals("type"))
      return count(items, 0, ruleValue);
    if (ruleKey.equals("color"))
      return count(items, 1, ruleValue);
    return count(items, 2, ruleValue);
  }

  private int count(List<List<String>> items, int index, final String ruleValue) {
    return (int) items.stream()
        .map(item -> item.get(index))
        .filter(s -> s.equals(ruleValue))
        .count();
  }

    public boolean isCorrect(List<List<String>> items, String ruleKey, String ruleValue, int output) {
        int expected = countMatches(items, ruleKey, ruleValue);
        return expected == output;
    }
}
