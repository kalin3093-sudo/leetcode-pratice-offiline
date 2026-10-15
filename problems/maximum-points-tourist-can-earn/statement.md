You are given two integers, `n` and `k`, along with two 2D integer arrays, `stayScore` and `travelScore`.

A tourist is visiting a country with `n` cities, where each city is **directly** connected to every other city. The tourist's journey consists of **exactly** `k` **0-indexed** days, and they can choose **any** city as their starting point.

Each day, the tourist has two choices:

-   **Stay in the current city**: If the tourist stays in their current city `curr` during day `i`, they will earn `stayScore[i][curr]` points.
-   **Move to another city**: If the tourist moves from their current city `curr` to city `dest`, they will earn `travelScore[curr][dest]` points.

Return the **maximum** possible points the tourist can earn.

**Constraints:**

-   `1 ≤ n ≤ 200`
-   `1 ≤ k ≤ 200`
-   `n == travelScore.length == travelScore[i].length == stayScore[i].length`
-   `k == stayScore.length`
-   `1 ≤ stayScore[i][j] ≤ 100`
-   `0 ≤ travelScore[i][j] ≤ 100`
-   `travelScore[i][i] == 0`
