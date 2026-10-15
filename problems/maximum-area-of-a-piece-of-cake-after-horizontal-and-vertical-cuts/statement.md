You are given a rectangular cake of size `h x w` and two arrays of integers `horizontalCuts` and `verticalCuts` where:

-   `horizontalCuts[i]` is the distance from the top of the rectangular cake to the `i^th` horizontal cut and similarly, and
-   `verticalCuts[j]` is the distance from the left of the rectangular cake to the `j^th` vertical cut.

Return *the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays* `horizontalCuts` *and* `verticalCuts`. Since the answer can be a large number, return this **modulo** `10^9 + 7`.

**Constraints:**

-   `2 ≤ h, w ≤ 10^9`
-   `1 ≤ horizontalCuts.length ≤ min(h - 1, 10^5)`
-   `1 ≤ verticalCuts.length ≤ min(w - 1, 10^5)`
-   `1 ≤ horizontalCuts[i] < h`
-   `1 ≤ verticalCuts[i] < w`
-   All the elements in `horizontalCuts` are distinct.
-   All the elements in `verticalCuts` are distinct.
