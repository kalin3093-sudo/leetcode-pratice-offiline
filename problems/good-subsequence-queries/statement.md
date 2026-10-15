You are given an integer array `nums` of length `n` and an integer `p`.

A **non-empty subsequence** of `nums` is called **good** if:

-   Its length is **strictly less** than `n`.
-   The **greatest common divisor (GCD)** of its elements is **exactly** `p`.

You are also given a 2D integer array `queries` of length `q`, where each `queries[i] = [indi, vali]` indicates that you should update `nums[indi]` to `vali`.

After each query, determine whether there exists **any good subsequence** in the current array.

Return the **number** of queries for which a **good subsequence** exists.

The term `gcd(a, b)` denotes the **greatest common divisor** of `a` and `b`.

**Constraints:**

-   `2 ≤ n == nums.length ≤ 5 * 10^4`
-   `1 ≤ nums[i] ≤ 5 * 10^4`
-   `1 ≤ queries.length ≤ 5 * 10^4`
-   `queries[i] = [indi, vali]`
-   `1 ≤ vali, p ≤ 5 * 10^4`
-   `0 ≤ indi ≤ n - 1`
