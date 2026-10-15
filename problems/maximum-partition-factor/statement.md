You are given a 2D integer array `points`, where `points[i] = [xi, yi]` represents the coordinates of the `i^th` point on the Cartesian plane.

The **Manhattan distance** between two points `points[i] = [xi, yi]` and `points[j] = [xj, yj]` is `|xi - xj| + |yi - yj|`.

Split the `n` points into **exactly two non-empty** groups. The **partition factor** of a split is the **minimum** Manhattan distance among all unordered pairs of points that lie in the same group.

Return the **maximum** possible **partition factor** over all valid splits.

Note: A group of size 1 contributes no intra-group pairs. When `n = 2` (both groups size 1), there are no intra-group pairs, so define the partition factor as 0.

**Constraints:**

-   `2 ≤ points.length ≤ 500`
-   `points[i] = [xi, yi]`
-   `-10^8 ≤ xi, yi ≤ 10^8`
