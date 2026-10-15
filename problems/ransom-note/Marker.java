import java.util.*;
class Marker {
  public boolean canConstruct(String ransomNote, String magazine) {
    int[] count = new int[26];

    for (final char c : magazine.toCharArray())
      ++count[c - 'a'];

    for (final char c : ransomNote.toCharArray()) {
      if (count[c - 'a'] == 0)
        return false;
      --count[c - 'a'];
    }

    return true;
  }

    public boolean isCorrect(String ransomNote, String magazine, boolean output) {
        boolean expected = canConstruct(ransomNote, magazine);
        return expected == output;
    }
}
