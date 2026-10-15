import java.util.*;
class Marker {
  public int countNodes(TreeNode root) {
    if (root == null)
      return 0;
    return 1 + countNodes(root.left) + countNodes(root.right);
  }

    public boolean isCorrect(TreeNode root, int output) {
        int expected = countNodes(root);
        return expected == output;
    }
}
