You are given a string `s` of length `n` and an integer array `order`, where `order` is a **permutation** of the numbers in the range `[0, n - 1]`.

Starting from time `t = 0`, replace the character at index `order[t]` in `s` with `'*'` at each time step.

A **substring** is **valid** if it contains **at least** one `'*'`.

A string is **active** if the total number of **valid** substrings is greater than or equal to `k`.

Return the **minimum** time `t` at which the string `s` becomes **active**. If it is impossible, return -1.

**Constraints:**

-   `1 ≤ n == s.length ≤ 10^5`
-   `order.length == n`
-   `0 ≤ order[i] ≤ n - 1`
-   `s` consists of lowercase English letters.
-   `order` is a permutation of integers from 0 to `n - 1`.
-   `1 ≤ k ≤ 10^9`
