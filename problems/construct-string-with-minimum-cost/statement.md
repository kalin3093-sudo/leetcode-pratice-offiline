You are given a string `target`, an array of strings `words`, and an integer array `costs`, both arrays of the same length.

Imagine an empty string `s`.

You can perform the following operation any number of times (including **zero**):

-   Choose an index `i` in the range `[0, words.length - 1]`.
-   Append `words[i]` to `s`.
-   The cost of operation is `costs[i]`.

Return the **minimum** cost to make `s` equal to `target`. If it's not possible, return `-1`.

**Constraints:**

-   `1 ≤ target.length ≤ 5 * 10^4`
-   `1 ≤ words.length == costs.length ≤ 5 * 10^4`
-   `1 ≤ words[i].length ≤ target.length`
-   The total sum of `words[i].length` is less than or equal to `5 * 10^4`.
-   `target` and `words[i]` consist only of lowercase English letters.
-   `1 ≤ costs[i] ≤ 10^4`
