You are given an integer array `nums` and an integer `sum`.

In one **operation**, choose an element with current value `x` and replace it with either `2 * x` or `floor(x / 2)`.

For each element, **multiplication** and **division** operations may be performed in any order.

Return the **minimum** number of operations needed so that some subset of the resulting array has a sum **exactly** equal to `sum`. If it is impossible, return -1.

The `floor()` function returns the integer part of the division.

**Constraints:**

-   `1 ≤ nums.length ≤ 100`
-   `1 ≤ nums[i] ≤ 500`
-   `1 ≤ sum ≤ 5000`
