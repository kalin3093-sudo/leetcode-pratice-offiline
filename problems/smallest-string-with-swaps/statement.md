You are given a string `s`, and an array of pairs of indices in the string `pairs` where `pairs[i] = [a, b]` indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given `pairs` **any number of times**.

Return the lexicographically smallest string that `s` can be changed to after using the swaps.

**Constraints:**

-   `1 ≤ s.length ≤ 10^5`
-   `0 ≤ pairs.length ≤ 10^5`
-   `0 ≤ pairs[i][0], pairs[i][1] < s.length`
-   `s` only contains lower case English letters.
