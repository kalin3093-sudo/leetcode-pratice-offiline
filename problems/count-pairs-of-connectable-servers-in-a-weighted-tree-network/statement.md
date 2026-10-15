You are given an unrooted weighted tree with `n` vertices representing servers numbered from `0` to `n - 1`, an array `edges` where `edges[i] = [ai, bi, weighti]` represents a bidirectional edge between vertices `ai` and `bi` of weight `weighti`. You are also given an integer `signalSpeed`.

Two servers `a` and `b` are **connectable** through a server `c` if:

-   `a < b`, `a != c` and `b != c`.
-   The distance from `c` to `a` is divisible by `signalSpeed`.
-   The distance from `c` to `b` is divisible by `signalSpeed`.
-   The path from `c` to `b` and the path from `c` to `a` do not share any edges.

Return *an integer array* `count` *of length* `n` *where* `count[i]` *is the **number** of server pairs that are **connectable** through* *the server* `i`.

**Constraints:**

-   `2 ≤ n ≤ 1000`
-   `edges.length == n - 1`
-   `edges[i].length == 3`
-   `0 ≤ ai, bi < n`
-   `edges[i] = [ai, bi, weighti]`
-   `1 ≤ weighti ≤ 10^6`
-   `1 ≤ signalSpeed ≤ 10^6`
-   The input is generated such that `edges` represents a valid tree.
