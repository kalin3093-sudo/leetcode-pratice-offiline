You are given a string `s`.

A string `t` is called **good** if all characters of `t` occur the same number of times.

You can perform the following operations **any number of times**:

-   Delete a character from `s`.
-   Insert a character in `s`.
-   Change a character in `s` to its next letter in the alphabet.

**Note** that you cannot change `'z'` to `'a'` using the third operation.

Return the **minimum** number of operations required to make `s` **good**.

**Constraints:**

-   `3 ≤ s.length ≤ 2 * 10^4`
-   `s` contains only lowercase English letters.
