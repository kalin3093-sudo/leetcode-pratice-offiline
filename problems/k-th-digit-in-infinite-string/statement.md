You are given an integer `k`.

An **infinite** string is formed by **concatenating** the **decimal** representations of the **positive** integers, without separators.

For every nonnegative integer `b`, block `b` contains the **positive** integers from `10 * b` through `10 * b + 9`. The integers in each block are appended as follows:

-   If `b` is even, append the integers in **increasing** order.
-   If `b` is odd, append the integers in **decreasing** order.

Therefore, the string starts with the integers 1 through 9, followed by 19 through 10, then 20 through 29, then 39 through 30, and so on.

Return the `k^th` digit (1-indexed) of this string.

**Constraints:**

-   `1 ≤ k ≤ 10^15`
