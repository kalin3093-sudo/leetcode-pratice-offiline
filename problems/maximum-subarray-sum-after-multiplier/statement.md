You are given an integer array `nums` and a positive integer `k`.

You must choose **exactly** one subarray of `nums` and perform **exactly** one of the following operations:

1.  Multiply each number in the chosen subarray by `k`.
2.  Divide each number in the chosen subarray by `k`.
    -   When dividing a positive number by `k`, use the **floor** value of the division result.
    -   When dividing a negative number by `k`, use the **ceiling** value of the division result.

Return the **maximum** possible sum of a **non-empty** subarray in the resulting array.

Note that the subarray chosen for the operation and the subarray chosen for the sum may be **different**.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `-10^5 ≤ nums[i] ≤ 10^5`
-   `1 ≤ k ≤ 10^5`
