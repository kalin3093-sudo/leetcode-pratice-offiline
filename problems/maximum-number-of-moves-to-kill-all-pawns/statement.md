There is a `50 x 50` chessboard with **one** knight and some pawns on it. You are given two integers `kx` and `ky` where `(kx, ky)` denotes the position of the knight, and a 2D array `positions` where `positions[i] = [xi, yi]` denotes the position of the pawns on the chessboard.

Alice and Bob play a *turn-based* game, where Alice goes first. In each player's turn:

-   The player *selects* a pawn that still exists on the board and captures it with the knight in the **fewest** possible **moves**. **Note** that the player can select **any** pawn, it **might not** be one that can be captured in the **least** number of moves.
-   In the process of capturing the *selected* pawn, the knight **may** pass other pawns **without** capturing them. **Only** the *selected* pawn can be captured in *this* turn.

Alice is trying to **maximize** the **sum** of the number of moves made by *both* players until there are no more pawns on the board, whereas Bob tries to **minimize** them.

Return the **maximum** *total* number of moves made during the game that Alice can achieve, assuming both players play **optimally**.

Note that in one **move,** a chess knight has eight possible positions it can move to, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.

![](https://assets.leetcode.com/uploads/2024/08/01/chess_knight.jpg)

**Constraints:**

-   `0 ≤ kx, ky ≤ 49`
-   `1 ≤ positions.length ≤ 15`
-   `positions[i].length == 2`
-   `0 ≤ positions[i][0], positions[i][1] ≤ 49`
-   All `positions[i]` are unique.
-   The input is generated such that `positions[i] != [kx, ky]` for all `0 ≤ i < positions.length`.
