You are given an integer `n` and an undirected tree with `n` nodes numbered from 0 to `n - 1`. The tree is represented by a 2D array `edges` of length `n - 1`, where `edges[i] = [ui, vi]` indicates an undirected edge between `ui` and `vi`.

You are also given three **distinct** target nodes `x`, `y`, and `z`.

For any node `u` in the tree:

-   Let `dx` be the distance from `u` to node `x`
-   Let `dy` be the distance from `u` to node `y`
-   Let `dz` be the distance from `u` to node `z`

The node `u` is called **special** if the three distances form a **Pythagorean Triplet**.

Return an integer denoting the number of special nodes in the tree.

A **Pythagorean triplet** consists of three integers `a`, `b`, and `c` which, when sorted in **ascending** order, satisfy `a^2 + b^2 = c^2`.

The **distance** between two nodes in a tree is the number of edges on the unique path between them.

**Constraints:**

-   `4 ≤ n ≤ 10^5`
-   `edges.length == n - 1`
-   `edges[i] = [ui, vi]`
-   `0 ≤ ui, vi, x, y, z ≤ n - 1`
-   `x`, `y`, and `z` are pairwise **distinct**.
-   The input is generated such that `edges` represent a valid tree.
