You are given an integer array `nums` and an integer `k`.

In one operation, you can **increase** or **decrease** any element of `nums` by 1.

An array is called **modulo alternating** if there exist two **distinct** integers `x` and `y` (`0 ≤ x, y < k`) such that:

-   For every **even** index `i`, `nums[i] % k == x`
-   For every **odd** index `i`, `nums[i] % k == y`

Return the **minimum** number of operations required to make `nums` **modulo alternating**.

**Constraints:**

-   `1 ≤ nums.length ≤ 100`
-   `1 ≤ nums[i] ≤ 10^9`
-   `2 ≤ k ≤ 100`
