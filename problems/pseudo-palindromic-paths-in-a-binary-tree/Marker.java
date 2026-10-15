import java.util.*;
class Marker {
    public int pseudoPalindromicPaths(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int mask) {
        if (node == null) return 0;
        mask ^= 1 << (node.val - 1);
        if (node.left == null && node.right == null)
            return (mask & (mask - 1)) == 0 ? 1 : 0;
        return dfs(node.left, mask) + dfs(node.right, mask);
    }

    public boolean isCorrect(TreeNode root, int output) {
        return pseudoPalindromicPaths(root) == output;
    }
}
