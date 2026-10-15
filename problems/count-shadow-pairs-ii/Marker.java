import java.util.*;

class Marker {
    public int shadowPairs(int[] nums) {
        return (int) solve(nums, 0, nums.length - 1);
    }

    private long solve(int[] nums, int l, int r) {
        if (l >= r) return 0;
        int mid = (l + r) / 2;
        long ans = solve(nums, l, mid) + solve(nums, mid + 1, r);
        ans += countCross(nums, l, mid, r);
        return ans;
    }

    private long countCross(int[] nums, int l, int mid, int r) {
        int leftSize = mid - l + 1;
        int rightSize = r - mid;
        int[] leftB = new int[leftSize];
        int[] rightC = new int[rightSize];

        TreeSet<Integer> leftVals = new TreeSet<>();
        for (int i = mid; i >= l; i--) {
            leftVals.add(nums[i]);
            Integer b = leftVals.higher(nums[i]);
            leftB[i - l] = (b == null) ? Integer.MAX_VALUE : b;
        }

        TreeSet<Integer> rightVals = new TreeSet<>();
        for (int j = mid + 1; j <= r; j++) {
            rightVals.add(nums[j]);
            Integer c = rightVals.lower(nums[j]);
            rightC[j - mid - 1] = (c == null) ? Integer.MIN_VALUE : c;
        }

        List<int[]> leftEvents = new ArrayList<>();
        for (int i = l; i <= mid; i++) {
            int val = nums[i];
            int b = leftB[i - l];
            if (val < b) {
                leftEvents.add(new int[]{val, b, i});
            }
        }
        leftEvents.sort(Comparator.comparingInt(a -> a[0]));

        List<Integer> rightIndices = new ArrayList<>();
        for (int j = mid + 1; j <= r; j++) rightIndices.add(j);
        rightIndices.sort(Comparator.comparingInt(j -> nums[j]));

        List<Integer> allVals = new ArrayList<>();
        for (int i = l; i <= mid; i++) allVals.add(nums[i]);
        for (int j = mid + 1; j <= r; j++) {
            allVals.add(nums[j]);
            allVals.add(rightC[j - mid - 1]);
        }
        allVals.add(Integer.MIN_VALUE);
        allVals.add(Integer.MAX_VALUE);
        Collections.sort(allVals);
        allVals = new ArrayList<>(new LinkedHashSet<>(allVals));
        Map<Integer, Integer> comp = new HashMap<>();
        for (int i = 0; i < allVals.size(); i++) comp.put(allVals.get(i), i + 1);
        int M = allVals.size() + 2;

        int[] bit = new int[M];
        int leftPtr = 0;
        List<int[]> active = new ArrayList<>();
        int activePtr = 0;
        long cross = 0;

        for (int j : rightIndices) {
            int valJ = nums[j];
            while (leftPtr < leftEvents.size() && leftEvents.get(leftPtr)[0] < valJ) {
                int[] ev = leftEvents.get(leftPtr);
                int i = ev[2];
                int ci = comp.get(nums[i]);
                add(bit, ci, 1);
                active.add(ev);
                leftPtr++;
            }
            while (activePtr < active.size() && active.get(activePtr)[1] < valJ) {
                int[] ev = active.get(activePtr);
                int i = ev[2];
                int ci = comp.get(nums[i]);
                add(bit, ci, -1);
                activePtr++;
            }
            int cj = rightC[j - mid - 1];
            int lo = comp.get(cj);
            cross += queryRange(bit, lo, M - 1);
        }
        return cross;
    }

    private void add(int[] bit, int i, int delta) {
        for (; i < bit.length; i += i & -i) bit[i] += delta;
    }

    private int query(int[] bit, int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) sum += bit[i];
        return sum;
    }

    private int queryRange(int[] bit, int l, int r) {
        if (l > r) return 0;
        return query(bit, r) - query(bit, l - 1);
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == shadowPairs(nums);
    }
}