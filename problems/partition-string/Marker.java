import java.util.*;

class Marker {
  public List<String> partitionString(String s) {
    List<String> res = new ArrayList<>();
    Set<String> seen = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      sb.append(s.charAt(i));
      String cur = sb.toString();
      if (!seen.contains(cur)) {
        seen.add(cur);
        res.add(cur);
        sb.setLength(0);
      }
    }
    return res;
  }

  public boolean isCorrect(String s, List<String> output) {
    return output != null && output.equals(partitionString(s));
  }
}
