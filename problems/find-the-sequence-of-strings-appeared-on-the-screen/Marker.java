import java.util.*;
class Marker {
  public List<String> stringSequence(String target) {
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    for (final char targetChar : target.toCharArray()) {
      sb.append('a');
      ans.add(sb.toString());
      for (char c = 'b'; c < targetChar; ++c) {
        sb.setCharAt(sb.length() - 1, c);
        ans.add(sb.toString());
      }
    }

    return ans;
  }

    public boolean isCorrect(String target, List<String> output) {
        if (target == null || output == null) return false;
        int expectedLen = 0;
        for (char c : target.toCharArray()) expectedLen += 1 + (c - 'a');
        if (expectedLen == 0 || output.size() != expectedLen) return false;
        if (!output.get(0).equals("a")) return false;
        String prev = output.get(0);
        for (int i = 1; i < output.size(); i++) {
            String cur = output.get(i);
            if (cur == null || cur.isEmpty()) return false;
            boolean appended = cur.length() == prev.length() + 1
                && cur.equals(prev + "a");
            char lastPrev = prev.charAt(prev.length() - 1);
            char nextChar = lastPrev == 'z' ? 'a' : (char) (lastPrev + 1);
            boolean incremented = cur.length() == prev.length()
                && cur.equals(prev.substring(0, prev.length() - 1) + nextChar);
            if (!appended && !incremented) return false;
            prev = cur;
        }
        return prev.equals(target);
    }
}
