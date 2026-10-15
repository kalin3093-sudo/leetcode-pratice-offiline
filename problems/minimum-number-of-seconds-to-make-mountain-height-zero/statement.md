You are given an integer `mountainHeight` denoting the height of a mountain.

You are also given an integer array `workerTimes` representing the work time of workers in **seconds**.

Each worker may reduce the mountain's height by any **non-negative integer** amount. If worker `i` reduces the height by `x`, then:

-   reducing the first unit of height takes `workerTimes[i]` seconds,
-   reducing the second unit takes `workerTimes[i] * 2` seconds,
-   ...
-   reducing the `x`\-th unit takes `workerTimes[i] * x` seconds.

The total time spent by worker `i` is the sum of the times required for all `x` units they reduce. As all workers operate simultaneously, the total time required is the **maximum** time spent by any worker.

Return an integer representing the **minimum** number of seconds required for the workers to make the height of the mountain 0.

**Constraints:**

-   `1 ≤ mountainHeight ≤ 10^5`
-   `1 ≤ workerTimes.length ≤ 10^4`
-   `1 ≤ workerTimes[i] ≤ 10^6`
