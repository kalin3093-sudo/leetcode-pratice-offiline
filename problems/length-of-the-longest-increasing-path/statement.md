You are given a 2D array of integers `coordinates` of length `n` and an integer `k`, where `0 ≤ k < n`.

`coordinates[i] = [xi, yi]` indicates the point `(xi, yi)` in a 2D plane.

An **increasing path** of length `m` is defined as a list of points `(x1, y1)`, `(x2, y2)`, `(x3, y3)`, ..., `(xm, ym)` such that:

-   `xi < xi + 1` and `yi < yi + 1` for all `i` where `1 ≤ i < m`.
-   `(xi, yi)` is in the given coordinates for all `i` where `1 ≤ i ≤ m`.

Return the **maximum** length of an **increasing path** that contains `coordinates[k]`.

**Constraints:**

-   `1 ≤ n == coordinates.length ≤ 10^5`
-   `coordinates[i].length == 2`
-   `0 ≤ coordinates[i][0], coordinates[i][1] ≤ 10^9`
-   All elements in `coordinates` are **distinct**.
-   `0 ≤ k ≤ n - 1`
