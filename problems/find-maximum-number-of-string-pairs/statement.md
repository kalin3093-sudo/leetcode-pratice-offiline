You are given a **0-indexed** array `words` consisting of **distinct** strings.

The string `words[i]` can be paired with the string `words[j]` if:

-   The string `words[i]` is equal to the reversed string of `words[j]`.
-   `0 ≤ i < j < words.length`.

Return *the **maximum** number of pairs that can be formed from the array* `words`*.*

Note that each string can belong in **at most one** pair.

**Constraints:**

-   `1 ≤ words.length ≤ 50`
-   `words[i].length == 2`
-   `words` consists of distinct strings.
-   `words[i]` contains only lowercase English letters.
