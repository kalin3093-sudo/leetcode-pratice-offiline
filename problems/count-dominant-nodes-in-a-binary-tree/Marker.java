import java.util.*;

public class Marker {

    public int countDominantNodes(TreeNode root) {
        if (root == null) return 0;
        List<TreeNode> order = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode u = q.poll();
            order.add(u);
            if (u.left != null) q.add(u.left);
            if (u.right != null) q.add(u.right);
        }
        Map<TreeNode, Integer> subMax = new HashMap<>();
        int count = 0;
        for (int i = order.size() - 1; i >= 0; i--) {
            TreeNode u = order.get(i);
            int m = u.val;
            if (u.left != null) m = Math.max(m, subMax.get(u.left));
            if (u.right != null) m = Math.max(m, subMax.get(u.right));
            subMax.put(u, m);
            if (u.val == m) count++;
        }
        return count;
    }

    public boolean isCorrect(TreeNode root, int out) {
        return countDominantNodes(root) == out;
    }
}
