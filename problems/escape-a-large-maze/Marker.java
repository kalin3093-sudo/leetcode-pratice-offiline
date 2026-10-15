import java.util.*;
class Marker {
  public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
    Set<Long> blockedSet = new HashSet<>();
    for (int[] b : blocked)
      blockedSet.add(hash(b[0], b[1]));

    return enclosed(blockedSet, source[0], source[1], hash(target[0], target[1])) &&
        enclosed(blockedSet, target[0], target[1], hash(source[0], source[1]));
  }

  // ponytail: iterative DFS + 32-bit key — recursion SOEs past ~10k frames; ((long)i<<16)+j
  // collides once j >= 65536 (coords go to 1e6). 19900-cell limit = (1+199)*199/2 enclosure.
  private boolean enclosed(Set<Long> blockedSet, int si, int sj, long target) {
    Set<Long> seen = new HashSet<>();
    Deque<Long> stack = new ArrayDeque<>();
    long start = hash(si, sj);
    seen.add(start);
    stack.push(start);
    while (!stack.isEmpty()) {
      long h = stack.pop();
      if (h == target || seen.size() > (1 + 199) * 199 / 2)
        return true;
      int i = (int) (h >> 32), j = (int) (h & 0xffffffffL);
      for (int[] d : new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }) {
        int ni = i + d[0], nj = j + d[1];
        if (ni < 0 || ni >= 1000000 || nj < 0 || nj >= 1000000)
          continue;
        long nh = hash(ni, nj);
        if (blockedSet.contains(nh) || seen.contains(nh))
          continue;
        seen.add(nh);
        stack.push(nh);
      }
    }
    return false;
  }

  private long hash(int i, int j) {
    return ((long) i << 32) | (j & 0xffffffffL);
  }

    public boolean isCorrect(int[][] blocked, int[] source, int[] target, boolean output) {
        boolean expected = isEscapePossible(blocked, source, target);
        return expected == output;
    }
}
