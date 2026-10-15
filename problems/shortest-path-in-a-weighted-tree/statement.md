You are given an integer `n` and an undirected, weighted tree rooted at node 1 with `n` nodes numbered from 1 to `n`. This is represented by a 2D array `edges` of length `n - 1`, where `edges[i] = [ui, vi, wi]` indicates an undirected edge from node `ui` to `vi` with weight `wi`.

You are also given a 2D integer array `queries` of length `q`, where each `queries[i]` is either:

-   `[1, u, v, w']` – **Update** the weight of the edge between nodes `u` and `v` to `w'`, where `(u, v)` is guaranteed to be an edge present in `edges`.
-   `[2, x]` – **Compute** the **shortest** path distance from the root node 1 to node `x`.

Return an integer array `answer`, where `answer[i]` is the **shortest** path distance from node 1 to `x` for the `i^th` query of `[2, x]`.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `edges.length == n - 1`
-   `edges[i] == [ui, vi, wi]`
-   `1 ≤ ui, vi ≤ n`
-   `1 ≤ wi ≤ 10^4`
-   The input is generated such that `edges` represents a valid tree.
-   `1 ≤ queries.length == q ≤ 10^5`
-   `queries[i].length == 2` or `4`
    -   `queries[i] == [1, u, v, w']` or,
    -   `queries[i] == [2, x]`
    -   `1 ≤ u, v, x ≤ n`
    -   `(u, v)` is always an edge from `edges`.
    -   `1 ≤ w' ≤ 10^4`
