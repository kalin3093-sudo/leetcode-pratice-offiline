**Special binary strings** are binary strings with the following two properties:

-   The number of `0`'s is equal to the number of `1`'s.
-   Every prefix of the binary string has at least as many `1`'s as `0`'s.

You are given a **special binary** string `s`.

A move consists of choosing two consecutive, non-empty, special substrings of `s`, and swapping them. Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.

Return *the lexicographically largest resulting string possible after applying the mentioned operations on the string*.

**Constraints:**

-   `1 ≤ s.length ≤ 50`
-   `s[i]` is either `'0'` or `'1'`.
-   `s` is a special binary string.
