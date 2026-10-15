import java.util.*;
class Marker {
  public String reformat(String s) {
    List<Character> A = new ArrayList<>();
    List<Character> B = new ArrayList<>();

    for (final char c : s.toCharArray())
      if (Character.isAlphabetic(c))
        A.add(c);
      else
        B.add(c);

    if (A.size() < B.size()) {
      List<Character> temp = A;
      A = B;
      B = temp;
    }
    if (A.size() - B.size() > 1)
      return "";

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < B.size(); ++i) {
      sb.append(A.get(i));
      sb.append(B.get(i));
    }

    if (A.size() > B.size())
      sb.append(A.get(A.size() - 1));
    return sb.toString();
  }

    public boolean isCorrect(String s, String output) {
        if (s == null || output == null) return false;
        int letters = 0, digits = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) letters++;
            else if (Character.isDigit(s.charAt(i))) digits++;
        }
        boolean impossible = Math.abs(letters - digits) > 1;
        if (output.isEmpty()) return impossible;
        if (impossible) return false;
        if (output.length() != s.length()) return false;
        int[] cnt = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 128) return false;
            cnt[c]++;
        }
        for (int i = 0; i < output.length(); i++) {
            char c = output.charAt(i);
            if (c >= 128) return false;
            if (--cnt[c] < 0) return false;
        }
        for (int i = 1; i < output.length(); i++)
            if (Character.isLetter(output.charAt(i - 1)) == Character.isLetter(output.charAt(i)))
                return false;
        return true;
    }
}
