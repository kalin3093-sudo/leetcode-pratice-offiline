Given the `root` of a binary tree and an integer `limit`, delete all **insufficient nodes** in the tree simultaneously, and return *the root of the resulting binary tree*.

A node is **insufficient** if every root to **leaf** path intersecting this node has a sum strictly less than `limit`.

A **leaf** is a node with no children.

**Constraints:**

-   The number of nodes in the tree is in the range `[1, 5000]`.
-   `-10^5 ≤ Node.val ≤ 10^5`
-   `-10^9 ≤ limit ≤ 10^9`
