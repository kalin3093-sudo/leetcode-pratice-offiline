Given two positive integers `n` and `k`, the binary string `Sn` is formed as follows:

-   `S1 = "0"`
-   `Si = Si - 1 + "1" + reverse(invert(Si - 1))` for `i > 1`

Where `+` denotes the concatenation operation, `reverse(x)` returns the reversed string `x`, and `invert(x)` inverts all the bits in `x` (`0` changes to `1` and `1` changes to `0`).

For example, the first four strings in the above sequence are:

-   `S1 = "0"`
-   `S2 = "0**1**1"`
-   `S3 = "011**1**001"`
-   `S4 = "0111001**1**0110001"`

Return *the* `k^th` *bit* *in* `Sn`. It is guaranteed that `k` is valid for the given `n`.

**Constraints:**

-   `1 ≤ n ≤ 20`
-   `1 ≤ k ≤ 2^n - 1`
