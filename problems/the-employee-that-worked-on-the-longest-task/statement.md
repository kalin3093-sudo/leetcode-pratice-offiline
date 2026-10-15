There are `n` employees, each with a unique id from `0` to `n - 1`.

You are given a 2D integer array `logs` where `logs[i] = [idi, leaveTimei]` where:

-   `idi` is the id of the employee that worked on the `i^th` task, and
-   `leaveTimei` is the time at which the employee finished the `i^th` task. All the values `leaveTimei` are **unique**.

Note that the `i^th` task starts the moment right after the `(i - 1)^th` task ends, and the `0^th` task starts at time `0`.

Return *the id of the employee that worked the task with the longest time.* If there is a tie between two or more employees, return *the **smallest** id among them*.

**Constraints:**

-   `2 ≤ n ≤ 500`
-   `1 ≤ logs.length ≤ 500`
-   `logs[i].length == 2`
-   `0 ≤ idi ≤ n - 1`
-   `1 ≤ leaveTimei ≤ 500`
-   `idi != idi+1`
-   `leaveTimei` are sorted in a strictly increasing order.
