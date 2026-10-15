You are given two integers `n` and `k`.

The **cost** of a binary string `s` is defined as the sum of all indices `i` (0-based) such that `s[i] == '1'`.

A binary string is considered **valid** if:

-   It does not contain two consecutive `'1'` characters.
-   Its cost is **less than or equal** to `k`.

Return a list of all valid binary strings of length `n` in any order.

**Constraints:**

-   `1 ≤ n ≤ 12`
-   `0 ≤ k ≤ n * (n - 1) / 2`
