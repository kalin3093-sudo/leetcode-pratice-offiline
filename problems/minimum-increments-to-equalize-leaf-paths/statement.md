You are given an integer `n` and an undirected tree rooted at node 0 with `n` nodes numbered from 0 to `n - 1`. This is represented by a 2D array `edges` of length `n - 1`, where `edges[i] = [ui, vi]` indicates an edge from node `ui` to `vi` .

Each node `i` has an associated cost given by `cost[i]`, representing the cost to traverse that node.

The **score** of a path is defined as the sum of the costs of all nodes along the path.

Your goal is to make the scores of all **root-to-leaf** paths **equal** by **increasing** the cost of any number of nodes by **any non-negative** amount.

Return the **minimum** number of nodes whose cost must be increased to make all root-to-leaf path scores equal.

**Constraints:**

-   `2 ≤ n ≤ 10^5`
-   `edges.length == n - 1`
-   `edges[i] == [ui, vi]`
-   `0 ≤ ui, vi < n`
-   `cost.length == n`
-   `1 ≤ cost[i] ≤ 10^9`
-   The input is generated such that `edges` represents a valid tree.
