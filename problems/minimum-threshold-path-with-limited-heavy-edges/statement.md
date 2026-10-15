There is an undirected weighted graph with `n` nodes labeled from 0 to `n - 1`.

The graph is represented by a 2D integer array `edges`, where each edge `edges[i] = [ui, vi, wi]` indicates that there is an undirected edge between nodes `ui` and `vi` with weight `wi`.

You are also given integers `source`, `target` and `k`.

A `threshold` value determines whether an edge is considered **light** or **heavy**:

-   An edge is **light** if its weight is **less than** or **equal** to `threshold`.
    
-   An edge is **heavy** if its weight is **greater than** `threshold`.
    

A path from `source` to `target` is **valid** if it contains **at most** `k` heavy edges.

Return the **minimum integer** `threshold` such that **at least** one **valid** path exists from `source` to `target`. If no such path exists, return -1.

**Constraints:**

-   `1 ≤ n ≤ 10^3`
-   `0 ≤ edges.length ≤ 10^3`
-   `edges[i] = [ui, vi, wi]`
-   `0 ≤ ui, vi ≤ n - 1`
-   `1 ≤ wi ≤ 10^9`
-   `0 ≤ source, target ≤ n - 1`
-   `0 ≤ k ≤ edges.length`
