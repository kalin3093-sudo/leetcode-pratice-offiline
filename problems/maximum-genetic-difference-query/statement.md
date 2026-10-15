There is a rooted tree consisting of `n` nodes numbered `0` to `n - 1`. Each node's number denotes its **unique genetic value** (i.e. the genetic value of node `x` is `x`). The **genetic difference** between two genetic values is defined as the **bitwise-****XOR** of their values. You are given the integer array `parents`, where `parents[i]` is the parent for node `i`. If node `x` is the **root** of the tree, then `parents[x] == -1`.

You are also given the array `queries` where `queries[i] = [nodei, vali]`. For each query `i`, find the **maximum genetic difference** between `vali` and `pi`, where `pi` is the genetic value of any node that is on the path between `nodei` and the root (including `nodei` and the root). More formally, you want to maximize `vali XOR pi`.

Return *an array* `ans` *where* `ans[i]` *is the answer to the* `i^th` *query*.

**Constraints:**

-   `2 ≤ parents.length ≤ 10^5`
-   `0 ≤ parents[i] ≤ parents.length - 1` for every node `i` that is **not** the root.
-   `parents[root] == -1`
-   `1 ≤ queries.length ≤ 3 * 10^4`
-   `0 ≤ nodei ≤ parents.length - 1`
-   `0 ≤ vali ≤ 2 * 10^5`
