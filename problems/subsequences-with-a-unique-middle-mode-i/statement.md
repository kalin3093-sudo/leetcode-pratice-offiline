Given an integer array `nums`, find the number of subsequences of size 5 of `nums` with a **unique middle mode**.

Since the answer may be very large, return it **modulo** `10^9 + 7`.

A **mode** of a sequence of numbers is defined as the element that appears the **maximum** number of times in the sequence.

A sequence of numbers contains a **unique mode** if it has only one mode.

A sequence of numbers `seq` of size 5 contains a **unique middle mode** if the *middle element* (`seq[2]`) is a **unique mode**.

**Constraints:**

-   `5 ≤ nums.length ≤ 1000`
-   `-10^9 ≤ nums[i] ≤ 10^9`
