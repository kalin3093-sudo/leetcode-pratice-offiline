You are given an array of strings `words` and an integer `k`.

Two words `a` and `b` at **distinct indices** are **prefix\-connected** if `a[0..k-1] == b[0..k-1]`.

A **connected group** is a set of words such that each pair of words is prefix-connected.

Return the **number of connected groups** that contain **at least** two words, formed from the given words.

**Note:**

-   Words with length less than `k` cannot join any group and are ignored.
-   Duplicate strings are treated as separate words.

**Constraints:**

-   `1 ≤ words.length ≤ 5000`
-   `1 ≤ words[i].length ≤ 100`
-   `1 ≤ k ≤ 100`
-   All strings in `words` consist of lowercase English letters.
