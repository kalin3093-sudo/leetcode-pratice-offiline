import java.util.*;
class Marker {
  public int generateKey(int num1, int num2, int num3) {
    final String s1 = String.format("%04d", num1);
    final String s2 = String.format("%04d", num2);
    final String s3 = String.format("%04d", num3);
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 4; ++i)
      sb.append((char) Math.min(Math.min(s1.charAt(i), s2.charAt(i)), s3.charAt(i)));

    return Integer.parseInt(sb.toString());
  }

    public boolean isCorrect(int num1, int num2, int num3, int output) {
        int expected = generateKey(num1, num2, num3);
        return expected == output;
    }
}
