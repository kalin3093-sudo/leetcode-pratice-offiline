You are given an array of strings `words` and a string `target`.

A string `x` is called **valid** if `x` is a prefix of **any** string in `words`.

Return the **minimum** number of **valid** strings that can be *concatenated* to form `target`. If it is **not** possible to form `target`, return `-1`.

**Constraints:**

-   `1 ≤ words.length ≤ 100`
-   `1 ≤ words[i].length ≤ 5 * 10^4`
-   The input is generated such that `sum(words[i].length) ≤ 10^5`.
-   `words[i]` consists only of lowercase English letters.
-   `1 ≤ target.length ≤ 5 * 10^4`
-   `target` consists only of lowercase English letters.
