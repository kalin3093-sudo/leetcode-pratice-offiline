You are given a 2D matrix `grid` of size `3 x 3` consisting only of characters `'B'` and `'W'`. Character `'W'` represents the white color, and character `'B'` represents the black color.

Your task is to change the color of **at most one** cell so that the matrix has a `2 x 2` square where all cells are of the same color.

Return `true` if it is possible to create a `2 x 2` square of the same color, otherwise, return `false`.

.grid-container { display: grid; grid-template-columns: 30px 30px 30px; padding: 10px; } .grid-item { background-color: black; border: 1px solid gray; height: 30px; font-size: 30px; text-align: center; } .grid-item-white { background-color: white; }

**Constraints:**

-   `grid.length == 3`
-   `grid[i].length == 3`
-   `grid[i][j]` is either `'W'` or `'B'`.
