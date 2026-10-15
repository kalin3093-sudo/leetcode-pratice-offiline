import java.util.*;

public class Marker {
    int[] bitArr;
    int bitN;

    void bitAdd(int i, int v) {
        for (int x = i + 1; x <= bitN; x += x & -x) bitArr[x] += v;
    }

    int bitSum(int i) {
        int s = 0;
        for (int x = i + 1; x > 0; x -= x & -x) s += bitArr[x];
        return s;
    }

    public long bowlSubarrays(int[] nums) {
        int n = nums.length;
        int[] nextGE = new int[n];
        int[] prevGE = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop();
            prevGE[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) st.pop();
            nextGE[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }
        bitArr = new int[n + 2];
        bitN = n;
        List<List<Integer>> expire = new ArrayList<>();
        for (int i = 0; i <= n; i++) expire.add(new ArrayList<>());
        for (int l = 0; l < n; l++) {
            if (nextGE[l] >= l + 2 && nextGE[l] < n) expire.get(nextGE[l] + 1).add(l);
        }
        long ans = 0;
        for (int r = 0; r < n; r++) {
            for (int l : expire.get(r)) bitAdd(l, -1);
            int lNew = r - 2;
            if (lNew >= 0 && nextGE[lNew] >= r) bitAdd(lNew, 1);
            int lo = Math.max(0, prevGE[r]);
            int hi = r - 2;
            if (lo <= hi) ans += bitSum(hi) - (lo > 0 ? bitSum(lo - 1) : 0);
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, long out) {
        return bowlSubarrays(nums) == out;
    }
}
