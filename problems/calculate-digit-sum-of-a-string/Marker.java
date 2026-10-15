import java.util.*;
class Marker {
  public String digitSum(String s, int k) {
    while (s.length() > k) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < s.length(); i += k) {
        int sum = 0;
        for (int j = i; j < Math.min(s.length(), i + k); ++j)
          sum += s.charAt(j) - '0';
        sb.append(sum);
      }
      s = sb.toString();
    }
    return s;
  }

    public boolean isCorrect(String s, int k, String output) {
        String expected = digitSum(s, k);
        return expected.equals(output);
    }
}
