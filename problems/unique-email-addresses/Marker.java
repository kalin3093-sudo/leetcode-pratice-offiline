import java.util.*;
class Marker {
  public int numUniqueEmails(String[] emails) {
    Set<String> normalized = new HashSet<>();

    for (final String email : emails) {
      String[] parts = email.split("@");
      String[] local = parts[0].split("\\+");
      normalized.add(local[0].replace(".", "") + "@" + parts[1]);
    }

    return normalized.size();
  }

    public boolean isCorrect(String[] emails, int output) {
        int expected = numUniqueEmails(emails);
        return expected == output;
    }
}
