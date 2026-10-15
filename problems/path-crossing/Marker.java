import java.util.*;

class Marker {
  public boolean isPathCrossing(String path) {
    Set<String> seen = new HashSet<>();
    seen.add("0,0");
    int x = 0, y = 0;
    for (char c : path.toCharArray()) {
      if (c == 'N')
        y++;
      else if (c == 'S')
        y--;
      else if (c == 'E')
        x++;
      else
        x--;
      if (!seen.add(x + "," + y))
        return true;
    }
    return false;
  }

  public boolean isCorrect(String path, boolean output) {
    return output == isPathCrossing(path);
  }
}
