You are given an integer `power` and two integer arrays `damage` and `health`, both having length `n`.

Bob has `n` enemies, where enemy `i` will deal Bob `damage[i]` **points** of damage per second while they are *alive* (i.e. `health[i] > 0`).

Every second, **after** the enemies deal damage to Bob, he chooses **one** of the enemies that is still *alive* and deals `power` points of damage to them.

Determine the **minimum** total amount of damage points that will be dealt to Bob before **all** `n` enemies are *dead*.

**Constraints:**

-   `1 ≤ power ≤ 10^4`
-   `1 ≤ n == damage.length == health.length ≤ 10^5`
-   `1 ≤ damage[i], health[i] ≤ 10^4`
