import java.util.*;
class Marker {
  public TreeNode bstFromPreorder(int[] preorder) {
    TreeNode root = new TreeNode(preorder[0]);
    Deque<TreeNode> stack = new ArrayDeque<>(List.of(root));

    for (int i = 1; i < preorder.length; ++i) {
      TreeNode parent = stack.peek();
      TreeNode child = new TreeNode(preorder[i]);
      // Adjust the parent.
      while (!stack.isEmpty() && stack.peek().val < child.val)
        parent = stack.pop();
      // Create parent-child link according to BST property.
      if (parent.val > child.val)
        parent.left = child;
      else
        parent.right = child;
      stack.push(child);
    }

    return root;
  }

    public boolean isCorrect(int[] preorder, TreeNode output) {
        TreeNode expected = bstFromPreorder(preorder);
        return sameTree(expected, output);
    }

    private static boolean sameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
}
