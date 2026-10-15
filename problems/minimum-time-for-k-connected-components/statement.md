You are given an integer `n` and an undirected graph with `n` nodes labeled from 0 to `n - 1`. This is represented by a 2D array `edges`, where `edges[i] = [ui, vi, timei]` indicates an undirected edge between nodes `ui` and `vi` that can be removed at `timei`.

You are also given an integer `k`.

Initially, the graph may be connected or disconnected. Your task is to find the **minimum** time `t` such that after removing all edges with `time ≤ t`, the graph contains **at least** `k` connected components.

Return the **minimum** time `t`.

A **connected component** is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `0 ≤ edges.length ≤ 10^5`
-   `edges[i] = [ui, vi, timei]`
-   `0 ≤ ui, vi < n`
-   `ui != vi`
-   `1 ≤ timei ≤ 10^9`
-   `1 ≤ k ≤ n`
-   There are no duplicate edges.
