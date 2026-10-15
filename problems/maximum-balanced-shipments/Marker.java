import java.util.*;

class Marker {
    public int maxBalancedShipments(int[] weight) {
        int n = weight.length;
        int[] leftGreater = new int[n];
        Arrays.fill(leftGreater, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && weight[stack.peek()] <= weight[i]) {
                stack.pop();
            }
            leftGreater[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        int[] dp = new int[n];
        int[] best = new int[n];
        for (int i = 0; i < n; i++) {
            int j = leftGreater[i];
            if (j != -1) {
                dp[i] = (j > 0 ? best[j - 1] : 0) + 1;
            }
            best[i] = dp[i];
            if (i > 0) best[i] = Math.max(best[i], best[i - 1]);
        }
        return best[n - 1];
    }

    public boolean isCorrect(int[] weight, int output) {
        return output == maxBalancedShipments(weight);
    }
}