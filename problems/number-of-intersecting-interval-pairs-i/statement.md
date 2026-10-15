You are given a 2D integer array `intervals` of `n` elements, where `intervals[i] = [starti, endi]` represents the **closed** interval from `starti` to `endi`.

Return the number of pairs of indices `(i, j)` such that `0 ≤ i < j < n` and `intervals[i]` and `intervals[j]` **intersect**.

Two intervals **intersect** if they have at least one point in common, including when they only share an endpoint.

**Constraints:**

-   `2 ≤ n == intervals.length ≤ 100`
-   `intervals[i] = [starti, endi]`
-   `0 ≤ starti ≤ endi ≤ 100`
