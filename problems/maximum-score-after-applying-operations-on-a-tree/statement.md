There is an undirected tree with `n` nodes labeled from 0 to `n - 1`, and rooted at node 0. You are given a 2D integer array `edges` of length `n - 1`, where `edges[i] = [ai, bi]` indicates that there is an edge between nodes `ai` and `bi` in the tree.

You are also given a **0-indexed** integer array `values` of length `n`, where `values[i]` is the **value** associated with the `i^th` node.

You start with a score of 0. In one operation, you choose a node `i`, **add** `values[i]` to your score, and then **set** `values[i]` to 0. All three steps happen together as a single operation.

A tree is **healthy** if, for **every** leaf node, the sum of the **current** values on the path from the root to that leaf is **not equal** to 0.

Return the **maximum score** you can obtain by performing this operation on the tree any number of times, so that the tree remains **healthy** at the end.

**Constraints:**

-   `2 ≤ n ≤ 2 * 10^4`
-   `edges.length == n - 1`
-   `edges[i].length == 2`
-   `0 ≤ ai, bi < n`
-   `values.length == n`
-   `1 ≤ values[i] ≤ 10^9`
-   The input is generated such that `edges` represents a valid tree.
