You are given an integer `n` and an undirected tree with `n` nodes numbered from 0 to `n - 1`. This is represented by a 2D array `edges` of length `n - 1`, where `edges[i] = [ui, vi]` indicates an undirected edge between nodes `ui` and `vi`.

You are also given an integer array `group` of length `n`, where `group[i]` denotes the group label assigned to node `i`.

-   Two nodes `u` and `v` are considered part of the same group if `group[u] == group[v]`.
-   The **interaction cost** between `u` and `v` is defined as the number of edges on the unique path connecting them in the tree.

Return an integer denoting the **sum** of interaction costs over all **unordered** pairs `(u, v)` with `u != v` such that `group[u] == group[v]`.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `edges.length == n - 1`
-   `edges[i] = [ui, vi]`
-   `0 ≤ ui, vi ≤ n - 1`
-   `group.length == n`
-   `1 ≤ group[i] ≤ 20`
-   The input is generated such that `edges` represents a valid tree.
