import java.util.*;
class Marker {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int m = walls.length;
        
        // Pair robots with distances and sort by position
        int[][] rd = new int[n][2];
        for (int i = 0; i < n; i++) {
            rd[i][0] = robots[i];
            rd[i][1] = distance[i];
        }
        Arrays.sort(rd, (a, b) -> Integer.compare(a[0], b[0]));
        
        Arrays.sort(walls);
        
        // dp[i][0] = max walls destroyed up to i-th robot, i-th robot shoots left
        // dp[i][1] = max walls destroyed up to i-th robot, i-th robot shoots right
        int[][] dp = new int[n][2];
        
        // Precompute wall counts in ranges
        // For each robot, we need to count walls in its shooting range
        // When robot i shoots left, range is [rd[i][0] - rd[i][1], rd[i][0]]
        // When robot i shoots right, range is [rd[i][0], rd[i][0] + rd[i][1]]
        // But range is blocked by previous/next robot
        
        for (int i = 0; i < n; i++) {
            int pos = rd[i][0];
            int dist = rd[i][1];
            
            // Shoot left
            int leftBound = pos - dist;
            if (i > 0) leftBound = Math.max(leftBound, rd[i - 1][0] + 1); // blocked by previous robot
            int leftWalls = countWalls(walls, leftBound, pos);
            
            if (i == 0) {
                dp[i][0] = leftWalls;
            } else {
                dp[i][0] = leftWalls + Math.max(dp[i - 1][0], dp[i - 1][1]);
            }
            
            // Shoot right
            int rightBound = pos + dist;
            if (i < n - 1) rightBound = Math.min(rightBound, rd[i + 1][0] - 1); // blocked by next robot
            int rightWalls = countWalls(walls, pos, rightBound);
            
            if (i == 0) {
                dp[i][1] = rightWalls;
            } else {
                dp[i][1] = rightWalls + Math.max(dp[i - 1][0], dp[i - 1][1]);
            }
        }
        
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
    
    private int countWalls(int[] walls, int left, int right) {
        if (left > right) return 0;
        int l = lowerBound(walls, left);
        int r = upperBound(walls, right);
        return r - l;
    }
    
    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
    
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    public boolean isCorrect(int[] robots, int[] distance, int[] walls, int output) {
        int expected = maxWalls(robots, distance, walls);
        return expected == output;
    }
}