You have the task of delivering some boxes from storage to their ports using only one ship. However, this ship has a **limit** on the **number of boxes** and the **total weight** that it can carry.

You are given an array `boxes`, where `boxes[i] = [portsi, weighti]`, and three integers `portsCount`, `maxBoxes`, and `maxWeight`.

-   `portsi` is the port where you need to deliver the `i^th` box and `weightsi` is the weight of the `i^th` box.
-   `portsCount` is the number of ports.
-   `maxBoxes` and `maxWeight` are the respective box and weight limits of the ship.

The boxes need to be delivered **in the order they are given**. The ship will follow these steps:

-   The ship will take some number of boxes from the `boxes` queue, not violating the `maxBoxes` and `maxWeight` constraints.
-   For each loaded box **in order**, the ship will make a **trip** to the port the box needs to be delivered to and deliver it. If the ship is already at the correct port, no **trip** is needed, and the box can immediately be delivered.
-   The ship then makes a return **trip** to storage to take more boxes from the queue.

The ship must end at storage after all the boxes have been delivered.

Return *the **minimum** number of **trips** the ship needs to make to deliver all boxes to their respective ports.*

**Constraints:**

-   `1 ≤ boxes.length ≤ 10^5`
-   `1 ≤ portsCount, maxBoxes, maxWeight ≤ 10^5`
-   `1 ≤ portsi ≤ portsCount`
-   `1 ≤ weightsi ≤ maxWeight`
