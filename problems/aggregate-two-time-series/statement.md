You are given two 2D integer arrays `series1` and `series2`.

Each element in both series is of the form `[timestamp, value]`, where:

-   `timestamp` is an integer representing the time.
-   `value` is an integer representing the value at that timestamp.

Each array is sorted in strictly increasing order of `timestamp`.

For any timestamp **not present** in a series, its value is taken from the **next available timestamp** in the same series if one exists. Otherwise, its value is considered 0.

The **aggregated series** is formed by summing the corresponding values from both series at every timestamp that appears in either series.

Return the **aggregated series** as a 2D integer array of `[timestamp, summedValue]` pairs, sorted in **strictly increasing** order of timestamp.

**Constraints:**

-   `1 ≤ series1.length, series2.length ≤ 10^5`
-   `series1[i].length == series2[i].length == 2`
-   `1 ≤ series1[i][0], series2[i][0] ≤ 10^9`
-   `1 ≤ series1[i][1], series2[i][1] ≤ 10^9`
-   Each series is sorted in strictly increasing order of `timestamp`.
