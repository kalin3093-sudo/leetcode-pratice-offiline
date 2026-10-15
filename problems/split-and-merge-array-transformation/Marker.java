import java.util.*;
class Marker {
    public int minSplitMerge(int[] nums1, int[] nums2) {
        String start = key(nums1), goal = key(nums2);
        if (start.equals(goal)) return 0;
        Map<String, Integer> dist = new HashMap<>();
        Queue<String> q = new ArrayDeque<>();
        dist.put(start, 0);
        q.offer(start);
        while (!q.isEmpty()) {
            String cur = q.poll();
            int d = dist.get(cur);
            int[] arr = toInts(cur);
            for (int L = 0; L < arr.length; L++) {
                for (int R = L; R < arr.length; R++) {
                    List<Integer> rest = new ArrayList<>();
                    for (int i = 0; i < L; i++) rest.add(arr[i]);
                    for (int i = R + 1; i < arr.length; i++) rest.add(arr[i]);
                    List<Integer> block = new ArrayList<>();
                    for (int i = L; i <= R; i++) block.add(arr[i]);
                    for (int pos = 0; pos <= rest.size(); pos++) {
                        List<Integer> nxt = new ArrayList<>(rest);
                        nxt.addAll(pos, block);
                        String k = key(nxt);
                        if (!dist.containsKey(k)) {
                            dist.put(k, d + 1);
                            if (k.equals(goal)) return d + 1;
                            q.offer(k);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private String key(int[] a) { return Arrays.toString(a); }
    private String key(List<Integer> a) { return a.toString(); }
    private int[] toInts(String s) {
        String t = s.substring(1, s.length() - 1);
        if (t.isEmpty()) return new int[0];
        String[] parts = t.split(", ");
        int[] r = new int[parts.length];
        for (int i = 0; i < parts.length; i++) r[i] = Integer.parseInt(parts[i].trim());
        return r;
    }

    public boolean isCorrect(int[] nums1, int[] nums2, int output) {
        return minSplitMerge(nums1, nums2) == output;
    }
}
