You are given an integer array `nums` of length `n` and an integer `maxVal`.

You **may** change any element in `nums` to any positive integer **less than or equal** to `maxVal`. Each such change costs 1.

Two integers are **co-prime** if their **greatest common divisor (GCD)** is 1.

After all modifications, you **must** choose an index `i` such that, `nums[i]` is **co-prime** with every other element `nums[j]`.

Let:

-   `selectedValue` be the final value of `nums[i]` after modifications.
-   `modificationCost` be the total number of elements changed.

The score is defined as `score = selectedValue - modificationCost`.

Return the **maximum** possible score.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^5`
-   `1 ≤ maxVal ≤ 10^5`
