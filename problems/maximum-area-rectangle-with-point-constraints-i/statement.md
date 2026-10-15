You are given an array `points` where `points[i] = [xi, yi]` represents the coordinates of a point on an infinite plane.

Your task is to find the **maximum** area of a rectangle that:

-   Can be formed using **four** of these points as its corners.
-   Does **not** contain any other point inside or on its border.
-   Has its edges **parallel** to the axes.

Return the **maximum area** that you can obtain or -1 if no such rectangle is possible.

**Constraints:**

-   `1 ≤ points.length ≤ 10`
-   `points[i].length == 2`
-   `0 ≤ xi, yi ≤ 100`
-   All the given points are **unique**.
