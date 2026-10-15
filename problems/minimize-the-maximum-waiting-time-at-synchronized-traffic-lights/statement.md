You are given an integer `period` and an integer array `lights`, where `lights[i]` is the duration, in seconds, of the green phase of the `i^th` traffic light.

At time 0, every traffic light starts at the beginning of its green phase. Their cycles are synchronized: every traffic light starts a new cycle at the same time, and every cycle lasts **exactly** `period` seconds. Therefore, the red phase of the `i^th` traffic light lasts for `period - lights[i]` seconds.

You are also given an integer array `arrivalTime`, where `arrivalTime[j]` is the arrival time, in seconds, of the `j^th` car.

Each car must be assigned to **exactly** one traffic light. Multiple cars may be assigned to the same traffic light. Any number of cars may cross the same traffic light simultaneously while it is green. Cars do not block or delay one another.

For a car `j` assigned to the `i^th` traffic light, let `r = arrivalTime[j] % period`. If `r < lights[i]`, its waiting time is 0. Otherwise, its waiting time is `period - r`.

The **penalty** of an assignment is the **maximum** waiting time among all cars.

Return an integer denoting the **minimum possible penalty**.

**Constraints:**

-   `2 ≤ period ≤ 10^9`
-   `1 ≤ lights.length ≤ 10^4`
-   `1 ≤ lights[i] ≤ period - 1`
-   `1 ≤ arrivalTime.length ≤ 10^5`
-   `1 ≤ arrivalTime[i] ≤ 10^9`
