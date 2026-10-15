import java.util.*;
class Marker {
  public TreeNode searchBST(TreeNode root, int val) {
    if (root == null)
      return null;
    if (root.val == val)
      return root;
    if (root.val > val)
      return searchBST(root.left, val);
    return searchBST(root.right, val);
  }

    public boolean isCorrect(TreeNode root, int val, TreeNode output) {
        TreeNode expected = searchBST(root, val);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
