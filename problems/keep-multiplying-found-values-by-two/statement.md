You are given an array of integers `nums`. You are also given an integer `original` which is the first number that needs to be searched for in `nums`.

You then do the following steps:

1.  If `original` is found in `nums`, **multiply** it by two (i.e., set `original = 2 * original`).
2.  Otherwise, **stop** the process.
3.  **Repeat** this process with the new number as long as you keep finding the number.

Return *the **final** value of* `original`.

**Constraints:**

-   `1 ≤ nums.length ≤ 1000`
-   `1 ≤ nums[i], original ≤ 1000`
