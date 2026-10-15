There is a hotel with `n` rooms. The rooms are represented by a 2D integer array `rooms` where `rooms[i] = [roomIdi, sizei]` denotes that there is a room with room number `roomIdi` and size equal to `sizei`. Each `roomIdi` is guaranteed to be **unique**.

You are also given `k` queries in a 2D array `queries` where `queries[j] = [preferredj, minSizej]`. The answer to the `j^th` query is the room number `id` of a room such that:

-   The room has a size of **at least** `minSizej`, and
-   `abs(id - preferredj)` is **minimized**, where `abs(x)` is the absolute value of `x`.

If there is a **tie** in the absolute difference, then use the room with the **smallest** such `id`. If there is **no such room**, the answer is `-1`.

Return *an array* `answer` *of length* `k` *where* `answer[j]` *contains the answer to the* `j^th` *query*.

**Constraints:**

-   `n == rooms.length`
-   `1 ≤ n ≤ 10^5`
-   `k == queries.length`
-   `1 ≤ k ≤ 10^4`
-   `1 ≤ roomIdi, preferredj ≤ 10^7`
-   `1 ≤ sizei, minSizej ≤ 10^7`
