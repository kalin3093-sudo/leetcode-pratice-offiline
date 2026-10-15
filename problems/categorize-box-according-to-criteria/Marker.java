import java.util.*;
class Marker {
  public String categorizeBox(int length, int width, int height, int mass) {
    final boolean isBulky = length >= 10000 || width >= 10000 || height >= 10000 ||
                            (long) length * width * height >= 1_000_000_000;
    final boolean isHeavy = mass >= 100;
    if (isBulky && isHeavy)
      return "Both";
    if (isBulky)
      return "Bulky";
    if (isHeavy)
      return "Heavy";
    return "Neither";
  }

    public boolean isCorrect(int length, int width, int height, int mass, String output) {
        String expected = categorizeBox(length, width, height, mass);
        return expected.equals(output);
    }
}
