You are given an undirected tree rooted at node 0 with `n` nodes numbered from 0 to `n - 1`. Each node `i` has an integer value `vals[i]`, and its parent is given by `par[i]`.

A **subset** of nodes within the **subtree** of a node is called **good** if every digit from 0 to 9 appears **at most** once in the decimal representation of the values of the selected nodes.

The **score** of a good subset is the sum of the values of its nodes.

Define an array `maxScore` of length `n`, where `maxScore[u]` represents the **maximum** possible sum of values of a good subset of nodes that belong to the subtree rooted at node `u`, including `u` itself and all its descendants.

Return the sum of all values in `maxScore`.

Since the answer may be large, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `1 ≤ n == vals.length ≤ 500`
-   `1 ≤ vals[i] ≤ 10^9`
-   `par.length == n`
-   `par[0] == -1`
-   `0 ≤ par[i] < n` for `i` in `[1, n - 1]`
-   The input is generated such that the parent array `par` represents a valid tree.
