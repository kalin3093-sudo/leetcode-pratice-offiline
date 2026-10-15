There is a class with `m` students and `n` exams. You are given a **0-indexed** `m x n` integer matrix `score`, where each row represents one student and `score[i][j]` denotes the score the `i^th` student got in the `j^th` exam. The matrix `score` contains **distinct** integers only.

You are also given an integer `k`. Sort the students (i.e., the rows of the matrix) by their scores in the `k^th` (**0-indexed**) exam from the highest to the lowest.

Return *the matrix after sorting it.*

**Constraints:**

-   `m == score.length`
-   `n == score[i].length`
-   `1 ≤ m, n ≤ 250`
-   `1 ≤ score[i][j] ≤ 10^5`
-   `score` consists of **distinct** integers.
-   `0 ≤ k < n`
