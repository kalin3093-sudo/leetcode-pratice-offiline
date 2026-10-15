You are given a 2D integer array `rectangles` where `rectangles[i] = [li, hi]` indicates that `i^th` rectangle has a length of `li` and a height of `hi`. You are also given a 2D integer array `points` where `points[j] = [xj, yj]` is a point with coordinates `(xj, yj)`.

The `i^th` rectangle has its **bottom-left corner** point at the coordinates `(0, 0)` and its **top-right corner** point at `(li, hi)`.

Return *an integer array* `count` *of length* `points.length` *where* `count[j]` *is the number of rectangles that **contain** the* `j^th` *point.*

The `i^th` rectangle **contains** the `j^th` point if `0 ≤ xj ≤ li` and `0 ≤ yj ≤ hi`. Note that points that lie on the **edges** of a rectangle are also considered to be contained by that rectangle.

**Constraints:**

-   `1 ≤ rectangles.length, points.length ≤ 5 * 10^4`
-   `rectangles[i].length == points[j].length == 2`
-   `1 ≤ li, xj ≤ 10^9`
-   `1 ≤ hi, yj ≤ 100`
-   All the `rectangles` are **unique**.
-   All the `points` are **unique**.
