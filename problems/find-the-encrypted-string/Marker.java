import java.util.*;
class Marker {
  public String getEncryptedString(String s, int k) {
    k %= s.length();
    return s.substring(k) + s.substring(0, k);
  }

    public boolean isCorrect(String s, int k, String output) {
        String expected = getEncryptedString(s, k);
        return expected.equals(output);
    }
}
