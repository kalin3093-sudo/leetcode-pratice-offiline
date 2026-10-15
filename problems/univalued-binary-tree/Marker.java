import java.util.*;
class Marker {
  public boolean isUnivalTree(TreeNode root) {
    if (root == null)
      return true;
    if (root.left != null && root.left.val != root.val)
      return false;
    if (root.right != null && root.right.val != root.val)
      return false;
    return isUnivalTree(root.left) && isUnivalTree(root.right);
  }

    public boolean isCorrect(TreeNode root, boolean output) {
        boolean expected = isUnivalTree(root);
        return expected == output;
    }
}
