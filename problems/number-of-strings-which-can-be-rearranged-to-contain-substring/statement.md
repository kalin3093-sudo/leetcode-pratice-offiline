You are given an integer `n`.

A string `s` is called **good** if it contains only lowercase English characters **and** it is possible to rearrange the characters of `s` such that the new string contains `"leet"` as a **substring**.

For example:

-   The string `"lteer"` is good because we can rearrange it to form `"leetr"` .
-   `"letl"` is not good because we cannot rearrange it to contain `"leet"` as a substring.

Return *the **total** number of good strings of length* `n`.

Since the answer may be large, return it **modulo** `10^9 + 7`.

A **substring** is a contiguous sequence of characters within a string.

**Constraints:**

-   `1 ≤ n ≤ 10^5`
