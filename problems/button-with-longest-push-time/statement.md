You are given a 2D array `events` which represents a sequence of events where a child pushes a series of buttons on a keyboard.

Each `events[i] = [indexi, timei]` indicates that the button at index `indexi` was pressed at time `timei`.

-   The array is **sorted** in increasing order of `time`.
-   The time taken to press a button is the difference in time between consecutive button presses. The time for the first button is simply the time at which it was pressed.

Return the `index` of the button that took the **longest** time to push. If multiple buttons have the same longest time, return the button with the **smallest** `index`.

**Constraints:**

-   `1 ≤ events.length ≤ 1000`
-   `events[i] == [indexi, timei]`
-   `1 ≤ indexi, timei ≤ 10^5`
-   The input is generated such that `events` is sorted in increasing order of `timei`.
