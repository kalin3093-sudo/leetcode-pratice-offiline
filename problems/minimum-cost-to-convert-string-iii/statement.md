You are given two strings, `source` and `target`.

You are also given a 2D string array `rules`, where `rules[i] = [patterni, replacementi]`, and an integer array `costs`, where `costs[i]` is the base cost of applying `rules[i]`. Both arrays have the same length. Additionally, `patterni` and `replacementi` have the same length.

You may apply **any** rule **any** number of times. Each rule application works as follows:

-   Choose an index `l` such that the range of positions from `l` to `l + patterni.length - 1` exists in the current string and **none** of these positions has been used in a previous rule application.
-   For each index `j`, the character `patterni[j]` must either be **equal** to the current character at position `l + j`, or be `'*'`.
-   Replace the characters in this range with `replacementi`. The replacement is used **exactly** as given and does not contain wildcards.
-   The cost of this rule application is `costs[i]` **plus** the number of `'*'` characters in `patterni`.
-   Once a character position has been used in a rule application, it **cannot** be used in any **later** rule application.

Since every `patterni` and `replacementi` have the same length, character positions are preserved after every rule application.

Return the **minimum** total cost required to transform `source` into `target`. If it is impossible, return -1.

**Constraints:**

-   `1 ≤ source.length == target.length ≤ 5000`
-   `source` and `target` consist of lowercase English letters.
-   `1 ≤ rules.length == costs.length ≤ 200`
-   `rules[i] = [patterni, replacementi]`
-   `1 ≤ patterni.length == replacementi.length ≤ 20`
-   `patterni` contains at least one lowercase English letter and at most 5 `'*'` characters.
-   `replacementi` contains only lowercase English letters.
-   `1 ≤ costs[i] ≤ 1000`
