import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int beautifulSubstrings(String s, int k) {
    final int root = getRoot(k);
    int ans = 0;
    int vowels = 0;
    int vowelsMinusConsonants = 0;
    // {(vowels, vowelsMinusConsonants): count}
    Map<Pair<Integer, Integer>, Integer> prefixCount = new HashMap<>();
    prefixCount.put(new Pair<>(0, 0), 1);

    for (final char c : s.toCharArray()) {
      if (isVowel(c)) {
        vowels = (vowels + 1) % root;
        ++vowelsMinusConsonants;
      } else {
        --vowelsMinusConsonants;
      }
      Pair<Integer, Integer> prefix = new Pair<>(vowels, vowelsMinusConsonants);
      ans += prefixCount.getOrDefault(prefix, 0);
      prefixCount.merge(prefix, 1, Integer::sum);
    }

    return ans;
  }

  private boolean isVowel(char c) {
    return "aeiou".indexOf(c) != -1;
  }

  private int getRoot(int k) {
    for (int i = 1; i <= k; ++i)
      if (i * i % k == 0)
        return i;
    throw new IllegalArgumentException();
  }

    public boolean isCorrect(String s, int k, int output) {
        int expected = beautifulSubstrings(s, k);
        return expected == output;
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(key, p.key) && Objects.equals(value, p.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
