You are given an array of strings `words`.

Define a **transformation** on a string `s` as follows:

-   Let `E` be the subsequence of characters at even indices of `s`.
-   Let `O` be the **subsequence** of characters at odd indices of `s`.
-   **Independently** cyclically shift `E` and `O` by **any** number of positions to the right, possibly zero.
-   Reconstruct the string by placing the shifted `E` characters back into even indices and the shifted `O` characters back into odd indices.

Two strings are **equivalent** if one can be transformed into the other by a **single** transformation.

Partition `words` into the **minimum** number of groups such that:

-   Every string belongs to **exactly** one group.
-   Every pair of strings in the same group are **equivalent**.

Return an integer denoting the **minimum** number of groups.

**Constraints:**

-   `1 ≤ words.length ≤ 10^5`
-   `1 ≤ words[i].length ≤ 5 * 10^5`
-   The sum of `words[i].length` does not exceed `5 * 10^5`.
-   `words[i]` consist of lowercase English letters.
