You are given an **undirected connected** graph of `n` nodes, numbered from `0` to `n - 1`. Each node is connected to **at most** 2 other nodes.

The graph consists of `m` edges, represented by a 2D array `edges`, where `edges[i] = [ai, bi]` indicates that there is an edge between nodes `ai` and `bi`.

You have to assign a **unique** value from `1` to `n` to each node. The value of an edge will be the **product** of the values assigned to the two nodes it connects.

Your score is the sum of the values of all edges in the graph.

Return the **maximum** score you can achieve.

**Constraints:**

-   `1 ≤ n ≤ 5 * 10^4`
-   `m == edges.length`
-   `1 ≤ m ≤ n`
-   `edges[i].length == 2`
-   `0 ≤ ai, bi < n`
-   `ai != bi`
-   There are no repeated edges.
-   The graph is connected.
-   Each node is connected to at most 2 other nodes.
