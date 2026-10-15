You are given an integer array `nums`.

You want to maximize the **alternating sum** of `nums`, which is defined as the value obtained by **adding** elements at even indices and **subtracting** elements at odd indices. That is, `nums[0] - nums[1] + nums[2] - nums[3]...`

You are also given a 2D integer array `swaps` where `swaps[i] = [pi, qi]`. For each pair `[pi, qi]` in `swaps`, you are allowed to swap the elements at indices `pi` and `qi`. These swaps can be performed any number of times and in any order.

Return the maximum possible **alternating sum** of `nums`.

**Constraints:**

-   `2 ≤ nums.length ≤ 10^5`
-   `1 ≤ nums[i] ≤ 10^9`
-   `0 ≤ swaps.length ≤ 10^5`
-   `swaps[i] = [pi, qi]`
-   `0 ≤ pi < qi ≤ nums.length - 1`
-   `[pi, qi] != [pj, qj]`
