import java.util.*;

class Marker {
  public int passwordStrength(String password) {
    Set<Character> seen = new HashSet<>();
    int ans = 0;
    for (char c : password.toCharArray()) {
      if (!seen.add(c))
        continue;
      if (c >= 'a' && c <= 'z')
        ans += 1;
      else if (c >= 'A' && c <= 'Z')
        ans += 2;
      else if (c >= '0' && c <= '9')
        ans += 3;
      else
        ans += 5;
    }
    return ans;
  }

  public boolean isCorrect(String password, int output) {
    return output == passwordStrength(password);
  }
}
