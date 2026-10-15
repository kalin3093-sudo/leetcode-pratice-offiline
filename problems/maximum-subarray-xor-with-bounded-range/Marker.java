import java.util.*;
class Marker {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[2];
        int count = 0;
    }
    
    public int maxXor(int[] nums, int k) {
        int n = nums.length;
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] ^ nums[i];
        }
        
        int ans = 0;
        int L = 0;
        Deque<Integer> maxQ = new ArrayDeque<>();
        Deque<Integer> minQ = new ArrayDeque<>();
        TrieNode root = new TrieNode();
        insert(root, pref[0]);
        
        for (int r = 0; r < n; r++) {
            while (!maxQ.isEmpty() && nums[maxQ.peekLast()] <= nums[r]) maxQ.pollLast();
            maxQ.offerLast(r);
            while (!minQ.isEmpty() && nums[minQ.peekLast()] >= nums[r]) minQ.pollLast();
            minQ.offerLast(r);
            
            while (nums[maxQ.peekFirst()] - nums[minQ.peekFirst()] > k) {
                if (maxQ.peekFirst() == L) maxQ.pollFirst();
                if (minQ.peekFirst() == L) minQ.pollFirst();
                remove(root, pref[L]);
                L++;
            }
            
            ans = Math.max(ans, query(root, pref[r + 1]));
            insert(root, pref[r + 1]);
        }
        
        return ans;
    }
    
    private void insert(TrieNode root, int x) {
        TrieNode node = root;
        for (int i = 14; i >= 0; i--) {
            int bit = (x >> i) & 1;
            if (node.child[bit] == null) node.child[bit] = new TrieNode();
            node = node.child[bit];
            node.count++;
        }
    }
    
    private void remove(TrieNode root, int x) {
        TrieNode node = root;
        for (int i = 14; i >= 0; i--) {
            int bit = (x >> i) & 1;
            node = node.child[bit];
            node.count--;
        }
    }
    
    private int query(TrieNode root, int x) {
        TrieNode node = root;
        int res = 0;
        for (int i = 14; i >= 0; i--) {
            int bit = (x >> i) & 1;
            int want = bit ^ 1;
            if (node.child[want] != null && node.child[want].count > 0) {
                res |= (1 << i);
                node = node.child[want];
            } else {
                node = node.child[bit];
            }
        }
        return res;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = maxXor(nums, k);
        return expected == output;
    }
}