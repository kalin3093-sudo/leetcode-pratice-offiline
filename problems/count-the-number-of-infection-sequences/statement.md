You are given an integer `n` and an array `sick` sorted in increasing order, representing positions of infected people in a line of `n` people.

At each step, **one** uninfected person **adjacent** to an infected person gets infected. This process continues until everyone is infected.

An **infection sequence** is the order in which uninfected people become infected, excluding those initially infected.

Return the number of different infection sequences possible, modulo `10^9+7`.

**Constraints:**

-   `2 ≤ n ≤ 10^5`
-   `1 ≤ sick.length ≤ n - 1`
-   `0 ≤ sick[i] ≤ n - 1`
-   `sick` is sorted in increasing order.
