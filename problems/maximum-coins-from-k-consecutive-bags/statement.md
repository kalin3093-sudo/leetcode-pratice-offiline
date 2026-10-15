There are an infinite amount of bags on a number line, one bag for each coordinate. Some of these bags contain coins.

You are given a 2D array `coins`, where `coins[i] = [li, ri, ci]` denotes that every bag from `li` to `ri` contains `ci` coins.

The segments that `coins` contain are non-overlapping.

You are also given an integer `k`.

Return the **maximum** amount of coins you can obtain by collecting `k` consecutive bags.

**Constraints:**

-   `1 ≤ coins.length ≤ 10^5`
-   `1 ≤ k ≤ 10^9`
-   `coins[i] == [li, ri, ci]`
-   `1 ≤ li ≤ ri ≤ 10^9`
-   `1 ≤ ci ≤ 1000`
-   The given segments are non-overlapping.
