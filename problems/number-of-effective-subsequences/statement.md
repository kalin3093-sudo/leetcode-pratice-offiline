You are given an integer array `nums`.

The **strength** of the array is defined as the **bitwise OR** of all its elements.

A **subsequence** is considered **effective** if removing that subsequence **strictly decreases** the strength of the remaining elements.

Return the number of **effective subsequences** in `nums`. Since the answer may be large, return it **modulo** `10^9 + 7`.

The bitwise OR of an empty array is 0.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^6`
