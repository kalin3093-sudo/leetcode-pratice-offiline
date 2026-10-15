There exists an infinitely large two-dimensional grid of uncolored unit cells. You are given a positive integer `n`, indicating that you must do the following routine for `n` minutes:

-   At the first minute, color **any** arbitrary unit cell blue.
-   Every minute thereafter, color blue **every** uncolored cell that touches a blue cell.

Below is a pictorial representation of the state of the grid after minutes 1, 2, and 3.

![](https://assets.leetcode.com/uploads/2023/01/10/example-copy-2.png)

Return *the number of **colored cells** at the end of* `n` *minutes*.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
