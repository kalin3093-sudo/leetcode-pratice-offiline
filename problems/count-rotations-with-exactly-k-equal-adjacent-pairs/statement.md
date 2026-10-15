You are given a string `s` of length `n` and an integer `k`.

A **cyclic rotation** of `s` is obtained by choosing a prefix of `s` whose length is between 0 and `n - 1` (inclusive), and moving it to the end of the string while preserving the order of all characters.

For **every** cyclic rotation of `s`, let its **score** be the number of indices `i` such that `0 ≤ i < n - 1` and the characters at positions `i` and `i + 1` are equal.

Return the number of cyclic rotations of `s` whose score equals `k`.

**Constraints:**

-   `2 ≤ n == s.length ≤ 100`
-   `s` only consists of lowercase English letters.
-   `0 ≤ k ≤ n - 1`
