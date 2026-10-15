import java.util.*;

class Marker {
  public long removeZeros(long n) {
    StringBuilder sb = new StringBuilder();
    for (char c : Long.toString(n).toCharArray())
      if (c != '0')
        sb.append(c);
    return Long.parseLong(sb.toString());
  }

  public boolean isCorrect(long n, long output) {
    return output == removeZeros(n);
  }
}
