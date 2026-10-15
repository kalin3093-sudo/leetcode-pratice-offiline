import java.util.*;
class Marker {
  public int videoStitching(int[][] clips, int time) {
    int ans = 0;
    int end = 0;
    int farthest = 0;

    Arrays.sort(clips, Comparator.comparingInt(clip -> clip[0]));

    int i = 0;
    while (farthest < time) {
      while (i < clips.length && clips[i][0] <= end)
        farthest = Math.max(farthest, clips[i++][1]);
      if (end == farthest)
        return -1;
      ++ans;
      end = farthest;
    }

    return ans;
  }

    public boolean isCorrect(int[][] clips, int time, int output) {
        int expected = videoStitching(clips, time);
        return expected == output;
    }
}
