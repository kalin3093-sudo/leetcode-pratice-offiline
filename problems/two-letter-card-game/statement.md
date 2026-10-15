You are given a deck of cards represented by a string array `cards`, and each card displays two lowercase letters.

You are also given a letter `x`. You play a game with the following rules:

-   Start with 0 points.
-   On each turn, you must find two **compatible** cards from the deck that both contain the letter `x` in any position.
-   Remove the pair of cards and earn **1 point**.
-   The game ends when you can no longer find a pair of compatible cards.

Return the **maximum** number of points you can gain with optimal play.

Two cards are **compatible** if the strings differ in **exactly** 1 position.

**Constraints:**

-   `2 ≤ cards.length ≤ 10^5`
-   `cards[i].length == 2`
-   Each `cards[i]` is composed of only lowercase English letters between `'a'` and `'j'`.
-   `x` is a lowercase English letter between `'a'` and `'j'`.
