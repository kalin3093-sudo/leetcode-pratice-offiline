You are given an **undirected tree** with `n` nodes, numbered from 0 to `n - 1`. It is represented by a 2D integer array `edges` of length `n - 1`, where `edges[i] = [ai, bi]` indicates that there is an edge between nodes `ai` and `bi` in the tree.

You are also given an integer array `good` of length `n`, where `good[i]` is 1 if the `i^th` node is good, and 0 if it is bad.

Define the **score** of a **subgraph** as the number of good nodes minus the number of bad nodes in that subgraph.

For each node `i`, find the **maximum** possible score among all **connected subgraphs** that contain node `i`.

Return an array of `n` integers where the `i^th` element is the **maximum** score for node `i`.

A **subgraph** is a graph whose vertices and edges are subsets of the original graph.

A **connected subgraph** is a subgraph in which every pair of its vertices is reachable from one another using only its edges.

**Constraints:**

-   `2 ≤ n ≤ 10^5`
-   `edges.length == n - 1`
-   `edges[i] = [ai, bi]`
-   `0 ≤ ai, bi < n`
-   `good.length == n`
-   `0 ≤ good[i] ≤ 1`
-   The input is generated such that `edges` represents a valid tree.
