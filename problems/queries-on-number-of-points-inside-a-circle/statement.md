You are given an array `points` where `points[i] = [xi, yi]` is the coordinates of the `i^th` point on a 2D plane. Multiple points can have the **same** coordinates.

You are also given an array `queries` where `queries[j] = [xj, yj, rj]` describes a circle centered at `(xj, yj)` with a radius of `rj`.

For each query `queries[j]`, compute the number of points **inside** the `j^th` circle. Points **on the border** of the circle are considered **inside**.

Return *an array* `answer`*, where* `answer[j]` *is the answer to the* `j^th` *query*.

**Constraints:**

-   `1 ≤ points.length ≤ 500`
-   `points[i].length == 2`
-   `0 ≤ xi, yi ≤ 500`
-   `1 ≤ queries.length ≤ 500`
-   `queries[j].length == 3`
-   `0 ≤ xj, yj ≤ 500`
-   `1 ≤ rj ≤ 500`
-   All coordinates are integers.

**Follow up:** Could you find the answer for each query in better complexity than `O(n)`?
