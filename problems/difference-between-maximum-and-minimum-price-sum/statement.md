There exists an undirected and initially unrooted tree with `n` nodes indexed from `0` to `n - 1`. You are given the integer `n` and a 2D integer array `edges` of length `n - 1`, where `edges[i] = [ai, bi]` indicates that there is an edge between nodes `ai` and `bi` in the tree.

Each node has an associated price. You are given an integer array `price`, where `price[i]` is the price of the `i^th` node.

The **price sum** of a given path is the sum of the prices of all nodes lying on that path.

The tree can be rooted at any node `root` of your choice. The incurred **cost** after choosing `root` is the difference between the maximum and minimum **price sum** amongst all paths starting at `root`.

Return *the **maximum** possible **cost*** *amongst all possible root choices*.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
-   `edges.length == n - 1`
-   `0 ≤ ai, bi ≤ n - 1`
-   `edges` represents a valid tree.
-   `price.length == n`
-   `1 ≤ price[i] ≤ 10^5`
