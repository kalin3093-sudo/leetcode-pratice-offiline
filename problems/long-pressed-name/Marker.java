import java.util.*;
class Marker {
  public boolean isLongPressedName(String name, String typed) {
    int i = 0;

    for (int j = 0; j < typed.length(); ++j)
      if (i < name.length() && name.charAt(i) == typed.charAt(j))
        ++i;
      else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1))
        return false;

    return i == name.length();
  }

    public boolean isCorrect(String name, String typed, boolean output) {
        boolean expected = isLongPressedName(name, typed);
        return expected == output;
    }
}
