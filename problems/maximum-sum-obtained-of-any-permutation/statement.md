We have an array of integers, `nums`, and an array of `requests` where `requests[i] = [starti, endi]`. The `i^th` request asks for the sum of `nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi]`. Both `starti` and `endi` are *0-indexed*.

Return *the maximum total sum of all requests **among all permutations** of* `nums`.

Since the answer may be too large, return it **modulo** `10^9 + 7`.

**Constraints:**

-   `n == nums.length`
-   `1 ≤ n ≤ 10^5`
-   `0 ≤ nums[i] ≤ 10^5`
-   `1 ≤ requests.length ≤ 10^5`
-   `requests[i].length == 2`
-   `0 ≤ starti ≤ endi < n`
