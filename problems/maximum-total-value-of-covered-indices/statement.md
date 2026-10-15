You are given an integer array `nums` of length `n` and a binary string `s` of length `n`, where `s[i] == '1'` means index `i` initially contains a **token** and `s[i] == '0'` means it does not.

You may perform the following operation any number of times:

-   Choose a token currently located at index `i`, where `i > 0`, such that this token has **not** been moved before.
-   Move this token from index `i` to index `i - 1`.

An index is considered **covered** if it contains a token after all moves.

Return an integer denoting the **maximum total value** of `nums` at the covered indices after optimally performing the operations.

**Constraints:**

-   `1 ≤ n == nums.length == s.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^5`
-   `s[i]` is either `'0'` or `'1'`
