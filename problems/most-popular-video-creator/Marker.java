import java.util.*;
class Creator {
  public long popularity; // the popularity sum
  public String videoId;  // the video id that has the maximum view
  public int maxView;     // the maximum view of the creator
  public Creator(long popularity, String videoId, int maxView) {
    this.popularity = popularity;
    this.videoId = videoId;
    this.maxView = maxView;
  }
}

class Marker {
  public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
    List<List<String>> ans = new ArrayList<>();
    Map<String, Creator> nameToCreator = new HashMap<>();
    long maxPopularity = 0;

    for (int i = 0; i < creators.length; ++i) {
      if (!nameToCreator.containsKey(creators[i])) {
        nameToCreator.put(creators[i], new Creator(views[i], ids[i], views[i]));
        maxPopularity = Math.max(maxPopularity, (long) views[i]);
        continue;
      }
      Creator creator = nameToCreator.get(creators[i]);
      creator.popularity += views[i];
      maxPopularity = Math.max(maxPopularity, (long) creator.popularity);
      if (creator.maxView < views[i] ||
          creator.maxView == views[i] && creator.videoId.compareTo(ids[i]) > 0) {
        creator.videoId = ids[i];
        creator.maxView = views[i];
      }
    }

    for (Map.Entry<String, Creator> entry : nameToCreator.entrySet())
      if (entry.getValue().popularity == maxPopularity)
        ans.add(Arrays.asList(entry.getKey(), entry.getValue().videoId));

    return ans;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(String[] creators, String[] ids, int[] views, List<List<String>> output) {
        List<List<String>> expected = mostPopularCreator(creators, ids, views);
        return sortedLL(expected).equals(sortedLL(output));
    }
}
