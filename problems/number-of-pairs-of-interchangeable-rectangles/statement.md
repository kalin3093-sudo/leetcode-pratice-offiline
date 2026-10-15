You are given `n` rectangles represented by a **0-indexed** 2D integer array `rectangles`, where `rectangles[i] = [widthi, heighti]` denotes the width and height of the `i^th` rectangle.

Two rectangles `i` and `j` (`i < j`) are considered **interchangeable** if they have the **same** width-to-height ratio. More formally, two rectangles are **interchangeable** if `widthi/heighti == widthj/heightj` (using decimal division, not integer division).

Return *the **number** of pairs of **interchangeable** rectangles in* `rectangles`.

**Constraints:**

-   `n == rectangles.length`
-   `1 ≤ n ≤ 10^5`
-   `rectangles[i].length == 2`
-   `1 ≤ widthi, heighti ≤ 10^5`
