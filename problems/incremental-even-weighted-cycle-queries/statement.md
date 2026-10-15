You are given a positive integer `n`.

There is an **undirected** graph with `n` nodes labeled from 0 to `n - 1`. Initially, the graph has no edges.

You are also given a 2D integer array `edges`, where `edges[i] = [ui, vi, wi]` represents an edge between nodes `ui` and `vi` with weight `wi`. The weight `wi` is either 0 or 1.

Process the edges in `edges` in the given order. For each edge, add it to the graph only if, after adding it, the sum of the weights of the edges in **every** cycle in the resulting graph is **even**.

Return an integer denoting the number of edges that are successfully added to the graph.

**Constraints:**

-   `3 ≤ n ≤ 5 * 10^4`
-   `1 ≤ edges.length ≤ 5 * 10^4`
-   `edges[i] = [ui, vi, wi]`
-   `0 ≤ ui < vi < n`
-   All edges are distinct.
-   `wi = 0 or wi = 1`
