You are given an undirected graph with `n` nodes labeled from 0 to `n - 1`. Node `i` has a **value** of `nums[i]`, which is either 0 or 1. The edges of the graph are given by a 2D array `edges` where `edges[i] = [ui, vi]` represents an edge between node `ui` and node `vi`.

For a **non-empty subset** `s` of nodes in the graph, we consider the **induced subgraph** of `s` generated as follows:

-   We keep only the nodes in `s`.
-   We keep only the edges whose two endpoints are both in `s`.

Return an integer representing the number of **non-empty** subsets `s` of nodes in the graph such that:

-   The **induced subgraph** of `s` is **connected**.
-   The **sum** of node **values** in `s` is **even**.

**Constraints:**

-   `1 ≤ n == nums.length ≤ 13`
-   `nums[i]` is 0 or 1.
-   `0 ≤ edges.length ≤ n * (n - 1) / 2`
-   `edges[i] = [ui, vi]`
-   `0 ≤ ui < vi < n`
-   All edges are **distinct**.
