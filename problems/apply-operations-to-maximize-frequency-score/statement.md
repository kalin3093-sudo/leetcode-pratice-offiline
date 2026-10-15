You are given a **0-indexed** integer array `nums` and an integer `k`.

You can perform the following operation on the array **at most** `k` times:

-   Choose any index `i` from the array and **increase** or **decrease** `nums[i]` by `1`.

The score of the final array is the **frequency** of the most frequent element in the array.

Return *the **maximum** score you can achieve*.

The frequency of an element is the number of occurences of that element in the array.

**Constraints:**

-   `1 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
-   `0 ≤ k ≤ 10^14`
