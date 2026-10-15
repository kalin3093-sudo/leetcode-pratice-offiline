You are given a 2D integer array `points` where `points[i] = [xi, yi, zi]` represents a point in 3D space, and an integer array `target` representing a target point.

Define **generation** 0 as the initial list of points. For each integer `k ≥ 1`, form generation `k` as follows:

-   Consider every pair of two **distinct** points `a = [x1, y1, z1]` and `b = [x2, y2, z2]` taken from all points produced in generations 0 through `k - 1`.
-   For each such pair, compute `c = [floor((x1 + x2) / 2), floor((y1 + y2) / 2), floor((z1 + z2) / 2)]` and collect every such `c` into a generation `k`.
-   All points in the generation `k` are produced **simultaneously** from points in generations 0 through `k - 1`.
-   After generation `k` is formed, the points in the generation `k` are considered available for forming later generations.

Return the **smallest** integer `k` such that the `target` appears in one of the generations 0 through `k`. If the `target` is already in the initial points, return 0. If it is impossible to obtain the `target`, return -1.

Notes:

-   **floor** denotes rounding **down** to the nearest integer.
-   "Two **distinct** points" means the two chosen points must have **different** `(x, y, z)` coordinates. A point cannot be paired with itself, and pairing two points with **identical** coordinates is not possible.

**Constraints:**

-   `1 ≤ points.length ≤ 20`
-   `points[i] = [xi, yi, zi]`
-   `0 ≤ xi, yi, zi ≤ 6`
-   `target.length == 3`
-   `0 ≤ target[i] ≤ 6`
-   The initial set of points contains no duplicates.
