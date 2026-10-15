You are given an array `items`, where each `items[i] = [typei, colori, namei]` describes the type, color, and name of the `i^th` item. You are also given a rule represented by two strings, `ruleKey` and `ruleValue`.

The `i^th` item is said to match the rule if **one** of the following is true:

-   `ruleKey == "type"` and `ruleValue == typei`.
-   `ruleKey == "color"` and `ruleValue == colori`.
-   `ruleKey == "name"` and `ruleValue == namei`.

Return *the number of items that match the given rule*.

**Constraints:**

-   `1 ≤ items.length ≤ 10^4`
-   `1 ≤ typei.length, colori.length, namei.length, ruleValue.length ≤ 10`
-   `ruleKey` is equal to either `"type"`, `"color"`, or `"name"`.
-   All strings consist only of lowercase letters.
