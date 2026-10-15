Given an array `rectangles` where `rectangles[i] = [xi, yi, ai, bi]` represents an axis-aligned rectangle. The bottom-left point of the rectangle is `(xi, yi)` and the top-right point of it is `(ai, bi)`.

Return `true` *if all the rectangles together form an exact cover of a rectangular region*.

**Constraints:**

-   `1 ≤ rectangles.length ≤ 2 * 10^4`
-   `rectangles[i].length == 4`
-   `-10^5 ≤ xi < ai ≤ 10^5`
-   `-10^5 ≤ yi < bi ≤ 10^5`
