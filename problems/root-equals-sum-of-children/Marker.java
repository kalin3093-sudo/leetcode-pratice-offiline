import java.util.*;
class Marker {
  public boolean checkTree(TreeNode root) {
    return root.val == root.left.val + root.right.val;
  }

    public boolean isCorrect(TreeNode root, boolean output) {
        boolean expected = checkTree(root);
        return expected == output;
    }
}
