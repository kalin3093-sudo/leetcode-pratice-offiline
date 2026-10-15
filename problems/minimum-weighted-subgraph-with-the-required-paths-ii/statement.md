You are given an **undirected weighted** tree with `n` nodes, numbered from `0` to `n - 1`. It is represented by a 2D integer array `edges` of length `n - 1`, where `edges[i] = [ui, vi, wi]` indicates that there is an edge between nodes `ui` and `vi` with weight `wi`.

Additionally, you are given a 2D integer array `queries`, where `queries[j] = [src1j, src2j, destj]`.

Return an array `answer` of length equal to `queries.length`, where `answer[j]` is the **minimum total weight** of a subtree such that it is possible to reach `destj` from both `src1j` and `src2j` using edges in this subtree.

A **subtree** here is any connected subset of nodes and edges of the original tree forming a valid tree.

**Constraints:**

-   `3 ≤ n ≤ 10^5`
-   `edges.length == n - 1`
-   `edges[i].length == 3`
-   `0 ≤ ui, vi < n`
-   `1 ≤ wi ≤ 10^4`
-   `1 ≤ queries.length ≤ 10^5`
-   `queries[j].length == 3`
-   `0 ≤ src1j, src2j, destj < n`
-   `src1j`, `src2j`, and `destj` are pairwise distinct.
-   The input is generated such that `edges` represents a valid tree.
