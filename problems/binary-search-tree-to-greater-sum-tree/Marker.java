import java.util.*;
class Marker {
    public TreeNode bstToGst(TreeNode root) {
        int[] sum = {0};
        revInorder(root, sum);
        return root;
    }

    private static void revInorder(TreeNode node, int[] sum) {
        if (node == null) return;
        revInorder(node.right, sum);
        sum[0] += node.val;
        node.val = sum[0];
        revInorder(node.left, sum);
    }

    private static boolean same(TreeNode a, TreeNode b) {
        if (a == null || b == null) return a == b;
        return a.val == b.val && same(a.left, b.left) && same(a.right, b.right);
    }

    public boolean isCorrect(TreeNode root, TreeNode output) {
        return same(bstToGst(root), output);
    }
}
