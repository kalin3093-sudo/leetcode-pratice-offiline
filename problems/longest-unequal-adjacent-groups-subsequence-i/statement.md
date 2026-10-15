You are given a string array `words` and a **binary** array `groups` both of length `n`.

A subsequence of `words` is **alternating** if for any two *consecutive* strings in the sequence, their corresponding elements at the *same* indices in `groups` are **different** (that is, there *cannot* be consecutive 0 or 1).

Your task is to select the **longest alternating** subsequence from `words`.

Return *the selected subsequence. If there are multiple answers, return **any** of them.*

**Note:** The elements in `words` are distinct.

**Constraints:**

-   `1 ≤ n == words.length == groups.length ≤ 100`
-   `1 ≤ words[i].length ≤ 10`
-   `groups[i]` is either `0` or `1.`
-   `words` consists of **distinct** strings.
-   `words[i]` consists of lowercase English letters.
