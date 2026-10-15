You are given a string `s` consisting of lowercase English letters.

You can perform the following operations any number of times (including zero) and in any order:

-   **Increment**: Choose any index `i` and replace `s[i]` with the next lowercase English letter. The letter after `'z'` is `'a'`.
-   **Left rotate**: Move the first character of the string to the end.

Return the **minimum** number of operations required to make `s` a palindrome.

**Constraints:**

-   `2 ≤ s.length ≤ 2000`
-   `s` consists only of lowercase English letters.
