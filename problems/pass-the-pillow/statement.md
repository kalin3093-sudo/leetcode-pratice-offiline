There are `n` people standing in a line labeled from `1` to `n`. The first person in the line is holding a pillow initially. Every second, the person holding the pillow passes it to the next person standing in the line. Once the pillow reaches the end of the line, the direction changes, and people continue passing the pillow in the opposite direction.

-   For example, once the pillow reaches the `n^th` person they pass it to the `n - 1^th` person, then to the `n - 2^th` person and so on.

Given the two positive integers `n` and `time`, return *the index of the person holding the pillow after* `time` *seconds*.

**Constraints:**

-   `2 ≤ n ≤ 1000`
-   `1 ≤ time ≤ 1000`

**Note:** This question is the same as [3178: Find the Child Who Has the Ball After K Seconds.](https://leetcode.com/problems/find-the-child-who-has-the-ball-after-k-seconds/description/)
