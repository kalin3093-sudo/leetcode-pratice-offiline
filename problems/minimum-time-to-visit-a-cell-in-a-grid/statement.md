You are given a `m x n` matrix `grid` consisting of **non-negative** integers where `grid[row][col]` represents the **minimum** time required to be able to visit the cell `(row, col)`, which means you can visit the cell `(row, col)` only when the time you visit it is greater than or equal to `grid[row][col]`.

You are standing in the **top-left** cell of the matrix in the `0^th` second, and you must move to **any** adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.

Return *the **minimum** time required in which you can visit the bottom-right cell of the matrix*. If you cannot visit the bottom-right cell, then return `-1`.

**Constraints:**

-   `m == grid.length`
-   `n == grid[i].length`
-   `2 ≤ m, n ≤ 1000`
-   `4 ≤ m * n ≤ 10^5`
-   `0 ≤ grid[i][j] ≤ 10^5`
-   `grid[0][0] == 0`

.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0; } .spoiler {overflow:hidden;} .spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;} .spoilerbutton\[value="Show Message"\] + .spoiler > div {margin-top:-500%;} .spoilerbutton\[value="Hide Message"\] + .spoiler {padding:5px;}
