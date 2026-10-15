You are given a 2D array `points` and a string `s` where, `points[i]` represents the coordinates of point `i`, and `s[i]` represents the **tag** of point `i`.

A **valid** square is a square centered at the origin `(0, 0)`, has edges parallel to the axes, and **does not** contain two points with the same tag.

Return the **maximum** number of points contained in a **valid** square.

Note:

-   A point is considered to be inside the square if it lies on or within the square's boundaries.
-   The side length of the square can be zero.

**Constraints:**

-   `1 ≤ s.length, points.length ≤ 10^5`
-   `points[i].length == 2`
-   `-10^9 ≤ points[i][0], points[i][1] ≤ 10^9`
-   `s.length == points.length`
-   `points` consists of distinct coordinates.
-   `s` consists only of lowercase English letters.
