Given a `m * n` matrix `seats`  that represent seats distributions in a classroom. If a seat is broken, it is denoted by `'#'` character otherwise it is denoted by a `'.'` character.

Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting directly in front or behind him. Return the **maximum** number of students that can take the exam together without any cheating being possible.

Students must be placed in seats in good condition.

**Constraints:**

-   `seats` contains only characters `'.' and``'#'.`
-   `m == seats.length`
-   `n == seats[i].length`
-   `1 ≤ m ≤ 8`
-   `1 ≤ n ≤ 8`
