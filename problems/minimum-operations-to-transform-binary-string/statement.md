You are given two binary strings `s1` and `s2` of the same length `n`.

You can perform the following operations on `s1` any number of times, in any order:

-   Choose an index `i` such that `s1[i] == '0'`, and change it to `'1'`.
-   Choose an index `i` such that `0 ≤ i < n - 1`, and both `s1[i]` and `s1[i + 1]` are `'1'`. Change both characters to `'0'`.

Return the **minimum** number of operations required to make `s1` equal to `s2`. If it is impossible, return -1.

**Constraints:**

-   `1 ≤ n == s1.length == s2.length ≤ 10^5`
-   `s1` and `s2` consist only of `'0'` and `'1'`.
