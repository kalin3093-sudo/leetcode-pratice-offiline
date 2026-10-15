You are given two integers, `l` and `r`, represented as strings, and an integer `b`. Return the count of integers in the inclusive range `[l, r]` whose digits are in **non-decreasing** order when represented in base `b`.

An integer is considered to have **non-decreasing** digits if, when read from left to right (from the most significant digit to the least significant digit), each digit is greater than or equal to the previous one.

Since the answer may be too large, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `1 ≤ l.length ≤ r.length ≤ 100`
-   `2 ≤ b ≤ 10`
-   `l` and `r` consist only of digits.
-   The value represented by `l` is less than or equal to the value represented by `r`.
-   `l` and `r` do not contain leading zeros.
