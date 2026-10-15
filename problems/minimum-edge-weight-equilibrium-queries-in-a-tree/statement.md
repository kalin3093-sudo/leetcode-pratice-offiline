There is an undirected tree with `n` nodes labeled from `0` to `n - 1`. You are given the integer `n` and a 2D integer array `edges` of length `n - 1`, where `edges[i] = [ui, vi, wi]` indicates that there is an edge between nodes `ui` and `vi` with weight `wi` in the tree.

You are also given a 2D integer array `queries` of length `m`, where `queries[i] = [ai, bi]`. For each query, find the **minimum number of operations** required to make the weight of every edge on the path from `ai` to `bi` equal. In one operation, you can choose any edge of the tree and change its weight to any value.

**Note** that:

-   Queries are **independent** of each other, meaning that the tree returns to its **initial state** on each new query.
-   The path from `ai` to `bi` is a sequence of **distinct** nodes starting with node `ai` and ending with node `bi` such that every two adjacent nodes in the sequence share an edge in the tree.

Return *an array* `answer` *of length* `m` *where* `answer[i]` *is the answer to the* `i^th` *query.*

**Constraints:**

-   `1 ≤ n ≤ 10^4`
-   `edges.length == n - 1`
-   `edges[i].length == 3`
-   `0 ≤ ui, vi < n`
-   `1 ≤ wi ≤ 26`
-   The input is generated such that `edges` represents a valid tree.
-   `1 ≤ queries.length == m ≤ 2 * 10^4`
-   `queries[i].length == 2`
-   `0 ≤ ai, bi < n`
