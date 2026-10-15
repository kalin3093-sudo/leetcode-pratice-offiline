You are given two 2D integer arrays, `items1` and `items2`, representing two sets of items. Each array `items` has the following properties:

-   `items[i] = [valuei, weighti]` where `valuei` represents the **value** and `weighti` represents the **weight** of the `i^th` item.
-   The value of each item in `items` is **unique**.

Return *a 2D integer array* `ret` *where* `ret[i] = [valuei, weighti]`*,* *with* `weighti` *being the **sum of weights** of all items with value* `valuei`.

**Note:** `ret` should be returned in **ascending** order by value.

**Constraints:**

-   `1 ≤ items1.length, items2.length ≤ 1000`
-   `items1[i].length == items2[i].length == 2`
-   `1 ≤ valuei, weighti ≤ 1000`
-   Each `valuei` in `items1` is **unique**.
-   Each `valuei` in `items2` is **unique**.
