You are given an integer `n` denoting the number of floors in a building, where the floors are numbered from 0 to `n - 1`.

You are also given an integer `start`, representing the floor where the elevator begins, and an integer array `requests`, where `requests[i]` is a floor that the elevator is requested to reach. All floors in `requests` are **distinct**.

At time 0, the elevator is on floor `start`, and all requests are made **simultaneously**.

During each second before all requests are fulfilled, the elevator moves **exactly** one floor, either **up** or **down**. A request is fulfilled **instantly** when the elevator reaches its requested floor. If `start` appears in `requests`, that request is fulfilled at time 0.

For each second that a request remains unfulfilled, you receive 1 penalty. Equivalently, a request fulfilled at time `t` contributes `t` to the total penalty.

Return the **minimum** total penalty required to fulfill all requests.

**Constraints:**

-   `1 ≤ n ≤ 10^9`
-   `1 ≤ requests.length ≤ 1500`
-   `0 ≤ start, requests[i] ≤ n - 1`
-   All values in `requests` are **distinct**.
