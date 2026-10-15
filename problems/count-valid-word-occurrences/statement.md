You are given an array of strings `chunks`. Concatenate all strings in `chunks` in order to form a string `s`.

You are also given an array of strings `queries`.

A **joiner hyphen** is a hyphen character `'-'` in `s` whose previous and next characters both exist and are lowercase English letters.

A **word** is a **maximal** substring of `s` consisting only of lowercase English letters and **joiner hyphens**.

All other characters, including spaces and hyphens that are not **joiner hyphens**, are treated as separators.

Return an integer array `ans`, where `ans[i]` is the number of times `queries[i]` appears as a word in `s`.

**Constraints:**

-   `1 ≤ chunks.length ≤ 10^5`
-   `1 ≤ chunks[i].length ≤ 10^5`
-   The total length of all strings in `chunks` does not exceed `10^5`.
-   `chunks[i]` consists only of lowercase English letters, spaces, and `'-'`.
-   `1 ≤ queries.length ≤ 10^5`
-   `1 ≤ queries[i].length ≤ 10^5`
-   The total length of all strings in `queries` does not exceed `10^5`.
-   `queries[i]` consists only of lowercase English letters and `'-'`.
-   `queries[i]` is a valid word: it does not start or end with `'-'`, and it does not contain two consecutive hyphens.
