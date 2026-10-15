import java.util.*;
class Marker {
  public List<String> removeSubfolders(String[] folder) {
    List<String> ans = new ArrayList<>();
    String prev = "";

    Arrays.sort(folder);

    for (final String f : folder) {
      if (!prev.isEmpty() && f.startsWith(prev) && f.charAt(prev.length()) == '/')
        continue;
      ans.add(f);
      prev = f;
    }

    return ans;
  }

    public boolean isCorrect(String[] folder, List<String> output) {
        List<String> expected = removeSubfolders(folder);
        return expected.equals(output);
    }
}
