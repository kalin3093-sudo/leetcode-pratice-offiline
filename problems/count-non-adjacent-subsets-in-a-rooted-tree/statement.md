You are given a rooted tree with `n` nodes labeled from 0 to `n - 1`, represented by an integer array `parent` of length `n`, where:

-   `parent[0] = -1` (node 0 is the root).
-   For each `1 ≤ i < n`, `parent[i]` is the parent of node `i` (`0 ≤ parent[i] < i`).

You are also given an integer array nums of length `n`, where `nums[i]` is the value of node `i`, and an integer `k`.

A non-empty subset of nodes is called **valid** if:

-   The **sum** of the values of the selected nodes is **divisible** by `k`.
-   No **two** selected nodes are **adjacent** in the tree (no node and its direct parent are both included in the subset).

Return the number of valid subsets modulo `10^9 + 7`.

**Constraints:**

-   `n == parent.length == nums.length`
-   `1 ≤ n ≤ 1000`
-   `parent[0] == -1`
-   For all `1 ≤ i < n`:
    -   `0 ≤ parent[i] < i`
-   `1 ≤ nums[i] ≤ 10^9`
-   `1 ≤ k ≤ 100`
-   `parent` describes a valid rooted tree.
