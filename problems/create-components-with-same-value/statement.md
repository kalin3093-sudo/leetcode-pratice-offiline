There is an undirected tree with `n` nodes labeled from `0` to `n - 1`.

You are given a **0-indexed** integer array `nums` of length `n` where `nums[i]` represents the value of the `i^th` node. You are also given a 2D integer array `edges` of length `n - 1` where `edges[i] = [ai, bi]` indicates that there is an edge between nodes `ai` and `bi` in the tree.

You are allowed to **delete** some edges, splitting the tree into multiple connected components. Let the **value** of a component be the sum of **all** `nums[i]` for which node `i` is in the component.

Return *the **maximum** number of edges you can delete, such that every connected component in the tree has the same value.*

**Constraints:**

-   `1 ≤ n ≤ 2 * 10^4`
-   `nums.length == n`
-   `1 ≤ nums[i] ≤ 50`
-   `edges.length == n - 1`
-   `edges[i].length == 2`
-   `0 ≤ edges[i][0], edges[i][1] ≤ n - 1`
-   `edges` represents a valid tree.
