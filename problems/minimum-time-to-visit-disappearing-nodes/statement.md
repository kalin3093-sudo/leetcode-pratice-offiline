There is an undirected graph of `n` nodes. You are given a 2D array `edges`, where `edges[i] = [ui, vi, lengthi]` describes an edge between node `ui` and node `vi` with a traversal time of `lengthi` units.

Additionally, you are given an array `disappear`, where `disappear[i]` denotes the time when the node `i` disappears from the graph and you won't be able to visit it.

**Note** that the graph might be *disconnected* and might contain *multiple edges*.

Return the array `answer`, with `answer[i]` denoting the **minimum** units of time required to reach node `i` from node 0. If node `i` is **unreachable** from node 0 then `answer[i]` is `-1`.

**Constraints:**

-   `1 ≤ n ≤ 5 * 10^4`
-   `0 ≤ edges.length ≤ 10^5`
-   `edges[i] == [ui, vi, lengthi]`
-   `0 ≤ ui, vi ≤ n - 1`
-   `1 ≤ lengthi ≤ 10^5`
-   `disappear.length == n`
-   `1 ≤ disappear[i] ≤ 10^5`
