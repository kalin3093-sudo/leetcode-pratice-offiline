You are given two integer arrays `x` and `y`, each of length `n`. You must choose three **distinct** indices `i`, `j`, and `k` such that:

-   `x[i] != x[j]`
-   `x[j] != x[k]`
-   `x[k] != x[i]`

Your goal is to **maximize** the value of `y[i] + y[j] + y[k]` under these conditions. Return the **maximum** possible sum that can be obtained by choosing such a triplet of indices.

If no such triplet exists, return -1.

**Constraints:**

-   `n == x.length == y.length`
-   `3 ≤ n ≤ 10^5`
-   `1 ≤ x[i], y[i] ≤ 10^6`
