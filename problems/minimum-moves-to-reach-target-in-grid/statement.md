You are given four integers `sx`, `sy`, `tx`, and `ty`, representing two points `(sx, sy)` and `(tx, ty)` on an infinitely large 2D grid.

You start at `(sx, sy)`.

At any point `(x, y)`, define `m = max(x, y)`. You can either:

-   Move to `(x + m, y)`, or
-   Move to `(x, y + m)`.

Return the **minimum** number of moves required to reach `(tx, ty)`. If it is impossible to reach the target, return -1.

**Constraints:**

-   `0 ≤ sx ≤ tx ≤ 10^9`
-   `0 ≤ sy ≤ ty ≤ 10^9`
