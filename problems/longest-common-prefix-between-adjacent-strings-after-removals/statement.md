You are given an array of strings `words`. For each index `i` in the range `[0, words.length - 1]`, perform the following steps:

-   Remove the element at index `i` from the `words` array.
-   Compute the **length** of the **longest common prefix** among all **adjacent** pairs in the modified array.

Return an array `answer`, where `answer[i]` is the length of the longest common prefix between the adjacent pairs after removing the element at index `i`. If **no** adjacent pairs remain or if **none** share a common prefix, then `answer[i]` should be 0.

**Constraints:**

-   `1 ≤ words.length ≤ 10^5`
-   `1 ≤ words[i].length ≤ 10^4`
-   `words[i]` consists of lowercase English letters.
-   The sum of `words[i].length` is smaller than or equal `10^5`.
