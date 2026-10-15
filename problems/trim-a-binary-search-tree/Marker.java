import java.util.*;
class Marker {
  public TreeNode trimBST(TreeNode root, int low, int high) {
    if (root == null)
      return null;
    if (root.val < low)
      return trimBST(root.right, low, high);
    if (root.val > high)
      return trimBST(root.left, low, high);
    root.left = trimBST(root.left, low, high);
    root.right = trimBST(root.right, low, high);
    return root;
  }

    public boolean isCorrect(TreeNode root, int low, int high, TreeNode output) {
        TreeNode expected = trimBST(root, low, high);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
