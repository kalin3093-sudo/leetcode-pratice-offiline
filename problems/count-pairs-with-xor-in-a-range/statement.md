Given a **(0-indexed)** integer array `nums` and two integers `low` and `high`, return *the number of **nice pairs***.

A **nice pair** is a pair `(i, j)` where `0 ≤ i < j < nums.length` and `low ≤ (nums[i] XOR nums[j]) ≤ high`.

**Constraints:**

-   `1 ≤ nums.length ≤ 2 * 10^4`
-   `1 ≤ nums[i] ≤ 2 * 10^4`
-   `1 ≤ low ≤ high ≤ 2 * 10^4`
