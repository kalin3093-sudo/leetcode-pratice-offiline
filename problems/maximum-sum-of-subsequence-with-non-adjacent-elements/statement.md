You are given an array `nums` consisting of integers. You are also given a 2D array `queries`, where `queries[i] = [posi, xi]`.

For query `i`, we first set `nums[posi]` equal to `xi`, then we calculate the answer to query `i` which is the **maximum** sum of a subsequence of `nums` where **no two adjacent elements are selected**.

Return the *sum* of the answers to all queries.

Since the final answer may be very large, return it **modulo** `10^9 + 7`.

A **subsequence** is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

**Constraints:**

-   `1 ≤ nums.length ≤ 5 * 10^4`
-   `-10^5 ≤ nums[i] ≤ 10^5`
-   `1 ≤ queries.length ≤ 5 * 10^4`
-   `queries[i] == [posi, xi]`
-   `0 ≤ posi ≤ nums.length - 1`
-   `-10^5 ≤ xi ≤ 10^5`
