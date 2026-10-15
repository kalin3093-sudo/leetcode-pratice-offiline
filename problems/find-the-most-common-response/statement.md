You are given a 2D string array `responses` where each `responses[i]` is an array of strings representing survey responses from the `i^th` day.

Return the **most common** response across all days after removing **duplicate** responses within each `responses[i]`. If there is a tie, return the *lexicographically smallest* response.

**Constraints:**

-   `1 ≤ responses.length ≤ 1000`
-   `1 ≤ responses[i].length ≤ 1000`
-   `1 ≤ responses[i][j].length ≤ 10`
-   `responses[i][j]` consists of only lowercase English letters
