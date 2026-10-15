import java.util.*;
class Marker {
    public int minOperations(int[] nums) {
        int N = 200005;
        boolean[] prime = new boolean[N];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i * i < N; i++)
            if (prime[i]) for (int j = i * i; j < N; j += i) prime[j] = false;
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            if (i % 2 == 0) {
                while (!prime[v]) v++;
                total += v - nums[i];
            } else {
                while (prime[v]) v++;
                total += v - nums[i];
            }
        }
        return total;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == minOperations(nums);
    }
}
