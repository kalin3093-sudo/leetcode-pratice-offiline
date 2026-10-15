You are given an integer array `nums` of size `n` where `n` is **even**, and an integer `k`.

You can perform some changes on the array, where in one change you can replace **any** element in the array with **any** integer in the range from `0` to `k`.

You need to perform some changes (possibly none) such that the final array satisfies the following condition:

-   There exists an integer `X` such that `abs(a[i] - a[n - i - 1]) = X` for all `(0 ≤ i < n)`.

Return the **minimum** number of changes required to satisfy the above condition.

**Constraints:**

-   `2 ≤ n == nums.length ≤ 10^5`
-   `n` is even.
-   `0 ≤ nums[i] ≤ k ≤ 10^5`
