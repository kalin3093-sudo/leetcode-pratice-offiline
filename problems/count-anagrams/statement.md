You are given a string `s` containing one or more words. Every consecutive pair of words is separated by a single space `' '`.

A string `t` is an **anagram** of string `s` if the `i^th` word of `t` is a **permutation** of the `i^th` word of `s`.

-   For example, `"acb dfe"` is an anagram of `"abc def"`, but `"def cab"` and `"adc bef"` are not.

Return *the number of **distinct anagrams** of* `s`. Since the answer may be very large, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `1 ≤ s.length ≤ 10^5`
-   `s` consists of lowercase English letters and spaces `' '`.
-   There is single space between consecutive words.
