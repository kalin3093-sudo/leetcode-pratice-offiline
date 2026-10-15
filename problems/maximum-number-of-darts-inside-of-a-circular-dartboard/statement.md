Alice is throwing `n` darts on a very large wall. You are given an array `darts` where `darts[i] = [xi, yi]` is the position of the `i^th` dart that Alice threw on the wall.

Bob knows the positions of the `n` darts on the wall. He wants to place a dartboard of radius `r` on the wall so that the maximum number of darts that Alice throws lie on the dartboard.

Given the integer `r`, return *the maximum number of darts that can lie on the dartboard*.

**Constraints:**

-   `1 ≤ darts.length ≤ 100`
-   `darts[i].length == 2`
-   `-10^4 ≤ xi, yi ≤ 10^4`
-   All the `darts` are unique
-   `1 ≤ r ≤ 5000`
