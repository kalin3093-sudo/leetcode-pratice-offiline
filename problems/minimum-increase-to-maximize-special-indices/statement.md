You are given an integer array `nums` of length `n`.

An index `i` (`0 < i < n - 1`) is **special** if `nums[i] > nums[i - 1]` and `nums[i] > nums[i + 1]`.

You may perform operations where you choose **any** index `i` and **increase** `nums[i]` by 1.

Your goal is to:

-   **Maximize** the number of **special** indices.
-   **Minimize** the total number of **operations** required to achieve that **maximum**.

Return an integer denoting the **minimum** total number of operations required.

**Constraints:**

-   `3 ≤ n ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
