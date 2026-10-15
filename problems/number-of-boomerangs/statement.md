You are given `n` `points` in the plane that are all **distinct**, where `points[i] = [xi, yi]`. A **boomerang** is a tuple of points `(i, j, k)` such that the distance between `i` and `j` equals the distance between `i` and `k` **(the order of the tuple matters)**.

Return *the number of boomerangs*.

**Constraints:**

-   `n == points.length`
-   `1 ≤ n ≤ 500`
-   `points[i].length == 2`
-   `-10^4 ≤ xi, yi ≤ 10^4`
-   All the points are **unique**.
