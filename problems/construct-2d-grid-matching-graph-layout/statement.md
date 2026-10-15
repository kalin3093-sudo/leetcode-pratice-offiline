You are given a 2D integer array `edges` representing an **undirected** graph having `n` nodes, where `edges[i] = [ui, vi]` denotes an edge between nodes `ui` and `vi`.

Construct a 2D grid that satisfies these conditions:

-   The grid contains **all nodes** from `0` to `n - 1` in its cells, with each node appearing exactly **once**.
-   Two nodes should be in adjacent grid cells (**horizontally** or **vertically**) **if and only if** there is an edge between them in `edges`.

It is guaranteed that `edges` can form a 2D grid that satisfies the conditions.

Return a 2D integer array satisfying the conditions above. If there are multiple solutions, return *any* of them.

**Constraints:**

-   `2 ≤ n ≤ 5 * 10^4`
-   `1 ≤ edges.length ≤ 10^5`
-   `edges[i] = [ui, vi]`
-   `0 ≤ ui < vi < n`
-   All the edges are distinct.
-   The input is generated such that `edges` can form a 2D grid that satisfies the conditions.
