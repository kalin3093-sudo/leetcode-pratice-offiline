You are given an array of strings `message` and an array of strings `bannedWords`.

An array of words is considered **spam** if there are **at least** two words in it that **exactly** match any word in `bannedWords`.

Return `true` if the array `message` is spam, and `false` otherwise.

**Constraints:**

-   `1 ≤ message.length, bannedWords.length ≤ 10^5`
-   `1 ≤ message[i].length, bannedWords[i].length ≤ 15`
-   `message[i]` and `bannedWords[i]` consist only of lowercase English letters.
