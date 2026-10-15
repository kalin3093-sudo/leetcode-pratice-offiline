You are given a list of songs where the `i^th` song has a duration of `time[i]` seconds.

Return *the number of pairs of songs for which their total duration in seconds is divisible by* `60`. Formally, we want the number of indices `i`, `j` such that `i < j` with `(time[i] + time[j]) % 60 == 0`.

**Constraints:**

-   `1 ≤ time.length ≤ 6 * 10^4`
-   `1 ≤ time[i] ≤ 500`
