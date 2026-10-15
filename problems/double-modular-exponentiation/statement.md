You are given a **0-indexed** 2D array `variables` where `variables[i] = [ai, bi, ci, mi]`, and an integer `target`.

An index `i` is **good** if the following formula holds:

-   `0 ≤ i < variables.length`
-   `((ai^bi % 10)^ci) % mi == target`

Return *an array consisting of **good** indices in **any order***.

**Constraints:**

-   `1 ≤ variables.length ≤ 100`
-   `variables[i] == [ai, bi, ci, mi]`
-   `1 ≤ ai, bi, ci, mi ≤ 10^3`
-   `0 ≤ target ≤ 10^3`
