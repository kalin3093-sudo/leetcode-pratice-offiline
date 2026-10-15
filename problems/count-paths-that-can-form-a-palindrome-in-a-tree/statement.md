You are given a **tree** (i.e. a connected, undirected graph that has no cycles) **rooted** at node `0` consisting of `n` nodes numbered from `0` to `n - 1`. The tree is represented by a **0-indexed** array `parent` of size `n`, where `parent[i]` is the parent of node `i`. Since node `0` is the root, `parent[0] == -1`.

You are also given a string `s` of length `n`, where `s[i]` is the character assigned to the edge between `i` and `parent[i]`. `s[0]` can be ignored.

Return *the number of pairs of nodes* `(u, v)` *such that* `u < v` *and the characters assigned to edges on the path from* `u` *to* `v` *can be **rearranged** to form a **palindrome***.

A string is a **palindrome** when it reads the same backwards as forwards.

**Constraints:**

-   `n == parent.length == s.length`
-   `1 ≤ n ≤ 10^5`
-   `0 ≤ parent[i] ≤ n - 1` for all `i ≥ 1`
-   `parent[0] == -1`
-   `parent` represents a valid tree.
-   `s` consists of only lowercase English letters.
