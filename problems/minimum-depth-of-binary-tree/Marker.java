import java.util.*;
class Marker {
  public int minDepth(TreeNode root) {
    if (root == null)
      return 0;
    if (root.left == null)
      return minDepth(root.right) + 1;
    if (root.right == null)
      return minDepth(root.left) + 1;
    return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
  }

    public boolean isCorrect(TreeNode root, int output) {
        int expected = minDepth(root);
        return expected == output;
    }
}
