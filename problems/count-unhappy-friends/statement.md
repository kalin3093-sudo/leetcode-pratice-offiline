You are given a list of `preferences` for `n` friends, where `n` is always **even**.

For each person `i`, `preferences[i]` contains a list of friends **sorted** in the **order of preference**. In other words, a friend earlier in the list is more preferred than a friend later in the list. Friends in each list are denoted by integers from `0` to `n-1`.

All the friends are divided into pairs. The pairings are given in a list `pairs`, where `pairs[i] = [xi, yi]` denotes `xi` is paired with `yi` and `yi` is paired with `xi`.

However, this pairing may cause some of the friends to be unhappy. A friend `x` is unhappy if `x` is paired with `y` and there exists a friend `u` who is paired with `v` but:

-   `x` prefers `u` over `y`, and
-   `u` prefers `x` over `v`.

Return *the number of unhappy friends*.

**Constraints:**

-   `2 ≤ n ≤ 500`
-   `n` is even.
-   `preferences.length == n`
-   `preferences[i].length == n - 1`
-   `0 ≤ preferences[i][j] ≤ n - 1`
-   `preferences[i]` does not contain `i`.
-   All values in `preferences[i]` are unique.
-   `pairs.length == n/2`
-   `pairs[i].length == 2`
-   `xi != yi`
-   `0 ≤ xi, yi ≤ n - 1`
-   Each person is contained in **exactly one** pair.
