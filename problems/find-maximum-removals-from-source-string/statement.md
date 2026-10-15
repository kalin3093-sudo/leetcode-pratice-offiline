You are given a string `source` of size `n`, a string `pattern` that is a subsequence of `source`, and a **sorted** integer array `targetIndices` that contains **distinct** numbers in the range `[0, n - 1]`.

We define an **operation** as removing a character at an index `idx` from `source` such that:

-   `idx` is an element of `targetIndices`.
-   `pattern` remains a subsequence of `source` after removing the character.

Performing an operation **does not** change the indices of the other characters in `source`. For example, if you remove `'c'` from `"acb"`, the character at index 2 would still be `'b'`.

Return the **maximum** number of *operations* that can be performed.

**Constraints:**

-   `1 ≤ n == source.length ≤ 3 * 10^3`
-   `1 ≤ pattern.length ≤ n`
-   `1 ≤ targetIndices.length ≤ n`
-   `targetIndices` is sorted in ascending order.
-   The input is generated such that `targetIndices` contains distinct elements in the range `[0, n - 1]`.
-   `source` and `pattern` consist only of lowercase English letters.
-   The input is generated such that `pattern` appears as a subsequence in `source`.
