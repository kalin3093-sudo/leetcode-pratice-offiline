You are given an integer array `nums` of length `n`.

For every **positive** integer `g`, we define the **beauty** of `g` as the **product** of `g` and the number of **strictly increasing** **subsequences** of `nums` whose greatest common divisor (GCD) is exactly `g`.

Return the **sum** of **beauty** values for all positive integers `g`.

Since the answer could be very large, return it modulo `10^9 + 7`.

**Constraints:**

-   `1 ≤ n == nums.length ≤ 10^4`
-   `1 ≤ nums[i] ≤ 7 * 10^4`
