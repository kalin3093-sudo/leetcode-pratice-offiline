import java.util.*;
class Marker {
  public String convertDateToBinary(String date) {
    final int year = Integer.parseInt(date.substring(0, 4));
    final int month = Integer.parseInt(date.substring(5, 7));
    final int day = Integer.parseInt(date.substring(8, 10));
    StringBuilder sb = new StringBuilder();
    sb.append(Integer.toBinaryString(year))
        .append("-")
        .append(Integer.toBinaryString(month))
        .append("-")
        .append(Integer.toBinaryString(day));
    return sb.toString();
  }

    public boolean isCorrect(String date, String output) {
        String expected = convertDateToBinary(date);
        return expected.equals(output);
    }
}
