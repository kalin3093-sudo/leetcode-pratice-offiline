You are given two integer arrays of size 2: `d = [d1, d2]` and `r = [r1, r2]`.

Two delivery drones are tasked with completing a specific number of deliveries. Drone `i` must complete `di` deliveries.

Each delivery takes **exactly** one hour and **only one** drone can make a delivery at any given hour.

Additionally, both drones require recharging at specific intervals during which they cannot make deliveries. Drone `i` must recharge every `ri` hours (i.e. at hours that are multiples of `ri`).

Return an integer denoting the **minimum** total time (in hours) required to complete all deliveries.

**Constraints:**

-   `d = [d1, d2]`
-   `1 ≤ di ≤ 10^9`
-   `r = [r1, r2]`
-   `2 ≤ ri ≤ 3 * 10^4`
