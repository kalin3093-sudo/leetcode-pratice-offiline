You are given two strings `s` and `t`, both of which are anagrams of each other, and an integer `k`.

Your task is to determine whether it is possible to split the string `s` into `k` equal-sized substrings, rearrange the substrings, and concatenate them in *any order* to create a new string that matches the given string `t`.

Return `true` if this is possible, otherwise, return `false`.

An **anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.

A **substring** is a contiguous **non-empty** sequence of characters within a string.

**Constraints:**

-   `1 ≤ s.length == t.length ≤ 2 * 10^5`
-   `1 ≤ k ≤ s.length`
-   `s.length` is divisible by `k`.
-   `s` and `t` consist only of lowercase English letters.
-   The input is generated such that `s` and `t` are anagrams of each other.
