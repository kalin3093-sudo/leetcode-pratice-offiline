You are given an integer `n` denoting the number of floors in a building, where the floors are numbered from 0 to `n - 1`.

You are also given an integer `start` and a 2D integer array `requests`, where `requests[i] = [arrivali, floori]` indicates that a request for `floori` is made at time `arrivali`.

At time 0, the elevator is at floor `start`.

At each second, the elevator may move **up** by 1 floor, move **down** by 1 floor, or **remain** on its current floor.

A request can be fulfilled **only** at or after its arrival time; it is fulfilled **instantly** when the elevator is on its requested floor at any time from its arrival time onward.

Return the **minimum** time needed to fulfill all requests.

**Constraints:**

-   `1 ≤ n ≤ 10^9`
-   `1 ≤ requests.length ≤ 16`
-   `requests[i] == [arrivali, floori]`
-   `0 ≤ arrivali ≤ 10^9`
-   `0 ≤ start, floori ≤ n - 1`
