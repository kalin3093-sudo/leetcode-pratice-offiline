There is an **undirected** tree with `n` nodes labeled from `0` to `n - 1`, and rooted at node `0`. You are given a 2D integer array `edges` of length `n - 1`, where `edges[i] = [ai, bi]` indicates that there is an edge between nodes `ai` and `bi` in the tree.

A node is **good** if all the subtrees rooted at its children have the same size.

Return the number of **good** nodes in the given tree.

A **subtree** of `treeName` is a tree consisting of a node in `treeName` and all of its descendants.

**Constraints:**

-   `2 ≤ n ≤ 10^5`
-   `edges.length == n - 1`
-   `edges[i].length == 2`
-   `0 ≤ ai, bi < n`
-   The input is generated such that `edges` represents a valid tree.
