You are given an integer `n` and a **Directed Acyclic Graph (DAG)** with `n` nodes labeled from 0 to `n - 1`. This is represented by a 2D array `edges`, where `edges[i] = [ui, vi, wi]` indicates a directed edge from node `ui` to `vi` with weight `wi`.

You are also given two integers, `k` and `t`.

Your task is to determine the **maximum** possible sum of edge weights for any path in the graph such that:

-   The path contains **exactly** `k` edges.
-   The total sum of edge weights in the path is **strictly** less than `t`.

Return the **maximum** possible sum of weights for such a path. If no such path exists, return `-1`.

**Constraints:**

-   `1 ≤ n ≤ 300`
-   `0 ≤ edges.length ≤ 300`
-   `edges[i] = [ui, vi, wi]`
-   `0 ≤ ui, vi < n`
-   `ui != vi`
-   `1 ≤ wi ≤ 10`
-   `0 ≤ k ≤ 300`
-   `1 ≤ t ≤ 600`
-   The input graph is **guaranteed** to be a **DAG**.
-   There are no duplicate edges.
