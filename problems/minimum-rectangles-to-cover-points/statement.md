You are given a 2D integer array `points`, where `points[i] = [xi, yi]`. You are also given an integer `w`. Your task is to **cover** **all** the given points with rectangles.

Each rectangle has its lower end at some point `(x1, 0)` and its upper end at some point `(x2, y2)`, where `x1 ≤ x2`, `y2 ≥ 0`, and the condition `x2 - x1 ≤ w` **must** be satisfied for each rectangle.

A point is considered covered by a rectangle if it lies within or on the boundary of the rectangle.

Return an integer denoting the **minimum** number of rectangles needed so that each point is covered by **at least one** rectangle*.*

**Note:** A point may be covered by more than one rectangle.

**Constraints:**

-   `1 ≤ points.length ≤ 10^5`
-   `points[i].length == 2`
-   `0 ≤ xi == points[i][0] ≤ 10^9`
-   `0 ≤ yi == points[i][1] ≤ 10^9`
-   `0 ≤ w ≤ 10^9`
-   All pairs `(xi, yi)` are distinct.
