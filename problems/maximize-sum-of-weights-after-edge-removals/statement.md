There exists an **undirected** tree with `n` nodes numbered `0` to `n - 1`. You are given a 2D integer array `edges` of length `n - 1`, where `edges[i] = [ui, vi, wi]` indicates that there is an edge between nodes `ui` and `vi` with weight `wi` in the tree.

Your task is to remove *zero or more* edges such that:

-   Each node has an edge with **at most** `k` other nodes, where `k` is given.
-   The sum of the weights of the remaining edges is **maximized**.

Return the **maximum** possible sum of weights for the remaining edges after making the necessary removals.

**Constraints:**

-   `2 ≤ n ≤ 10^5`
-   `1 ≤ k ≤ n - 1`
-   `edges.length == n - 1`
-   `edges[i].length == 3`
-   `0 ≤ edges[i][0] ≤ n - 1`
-   `0 ≤ edges[i][1] ≤ n - 1`
-   `1 ≤ edges[i][2] ≤ 10^6`
-   The input is generated such that `edges` form a valid tree.
