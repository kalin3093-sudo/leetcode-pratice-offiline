You are given three strings `word1`, `word2`, and `target`.

Your task is to count the number of ways to form `target` by choosing characters from `word1` and `word2` under the following conditions:

-   For each character of `target`, choose one matching character from either `word1` or `word2`.
-   The chosen indices from `word1` must be **strictly** increasing.
-   The chosen indices from `word2` must be **strictly** increasing.
-   **At least** one character must be chosen from **both** `word1` and `word2`.

Two ways are considered different if, for **at least** one position in `target`, the chosen character comes from a different string or a different index.

Return the number of ways. Since the answer may be very large, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `1 ≤ word1.length, word2.length, target.length ≤ 100`
-   `word1`, `word2`, and `target` consist of lowercase English letters only.
