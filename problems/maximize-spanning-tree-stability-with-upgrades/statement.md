You are given an integer `n`, representing `n` nodes numbered from 0 to `n - 1` and a list of `edges`, where `edges[i] = [ui, vi, si, musti]`:

-   `ui` and `vi` indicates an undirected edge between nodes `ui` and `vi`.
-   `si` is the strength of the edge.
-   `musti` is an integer (0 or 1). If `musti == 1`, the edge **must** be included in the **spanning tree**. These edges **cannot** be **upgraded**.

You are also given an integer `k`, the **maximum** number of upgrades you can perform. Each upgrade **doubles** the strength of an edge, and each eligible edge (with `musti == 0`) can be upgraded **at most** once.

The **stability** of a spanning tree is defined as the **minimum** strength score among all edges included in it.

Return the **maximum** possible stability of any valid spanning tree. If it is impossible to connect all nodes, return `-1`.

**Note**: A **spanning tree** of a graph with `n` nodes is a subset of the edges that connects all nodes together (i.e. the graph is **connected**) *without* forming any cycles, and uses **exactly** `n - 1` edges.

**Constraints:**

-   `2 ≤ n ≤ 10^5`
-   `1 ≤ edges.length ≤ 10^5`
-   `edges[i] = [ui, vi, si, musti]`
-   `0 ≤ ui, vi < n`
-   `ui != vi`
-   `1 ≤ si ≤ 10^5`
-   `musti` is either `0` or `1`.
-   `0 ≤ k ≤ n`
-   There are no duplicate edges.
