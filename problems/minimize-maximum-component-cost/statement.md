You are given an undirected connected graph with `n` nodes labeled from 0 to `n - 1` and a 2D integer array `edges` where `edges[i] = [ui, vi, wi]` denotes an undirected edge between node `ui` and node `vi` with weight `wi`, and an integer `k`.

You are allowed to remove any number of edges from the graph such that the resulting graph has **at most** `k` connected components.

The **cost** of a component is defined as the **maximum** edge weight in that component. If a component has no edges, its cost is 0.

Return the **minimum** possible value of the **maximum** cost among all components **after such removals**.

**Constraints:**

-   `1 ≤ n ≤ 5 * 10^4`
-   `0 ≤ edges.length ≤ 10^5`
-   `edges[i].length == 3`
-   `0 ≤ ui, vi < n`
-   `1 ≤ wi ≤ 10^6`
-   `1 ≤ k ≤ n`
-   The input graph is connected.
