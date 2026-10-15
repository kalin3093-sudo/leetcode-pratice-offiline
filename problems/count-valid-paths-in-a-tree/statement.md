There is an undirected tree with `n` nodes labeled from `1` to `n`. You are given the integer `n` and a 2D integer array `edges` of length `n - 1`, where `edges[i] = [ui, vi]` indicates that there is an edge between nodes `ui` and `vi` in the tree.

Return *the **number of valid paths** in the tree*.

A path `(a, b)` is **valid** if there exists **exactly one** prime number among the node labels in the path from `a` to `b`.

**Note** that:

-   The path `(a, b)` is a sequence of **distinct** nodes starting with node `a` and ending with node `b` such that every two adjacent nodes in the sequence share an edge in the tree.
-   Path `(a, b)` and path `(b, a)` are considered the **same** and counted only **once**.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `edges.length == n - 1`
-   `edges[i].length == 2`
-   `1 ≤ ui, vi ≤ n`
-   The input is generated such that `edges` represent a valid tree.
