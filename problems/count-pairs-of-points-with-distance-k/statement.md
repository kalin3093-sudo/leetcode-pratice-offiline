You are given a **2D** integer array `coordinates` and an integer `k`, where `coordinates[i] = [xi, yi]` are the coordinates of the `i^th` point in a 2D plane.

We define the **distance** between two points `(x1, y1)` and `(x2, y2)` as `(x1 XOR x2) + (y1 XOR y2)` where `XOR` is the bitwise `XOR` operation.

Return *the number of pairs* `(i, j)` *such that* `i < j` *and the distance between points* `i` *and* `j` *is equal to* `k`.

**Constraints:**

-   `2 ≤ coordinates.length ≤ 50000`
-   `0 ≤ xi, yi ≤ 10^6`
-   `0 ≤ k ≤ 100`
