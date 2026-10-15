You are given a collection of numbered `balls` and instructed to sort them into boxes for a nearly balanced distribution. There are two rules you must follow:

-   Balls with the same box must have the same value. But, if you have more than one ball with the same number, you can put them in different boxes.
-   The biggest box can only have one more ball than the smallest box.

Return the *fewest number of boxes* to sort these balls following these rules.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
