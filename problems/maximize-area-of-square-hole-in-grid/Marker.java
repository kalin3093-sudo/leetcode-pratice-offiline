import java.util.*;
class Marker {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxH = longestConsecutive(hBars);
        int maxV = longestConsecutive(vBars);
        int len = Math.min(maxH + 1, maxV + 1);
        return len * len;
    }
    
    private int longestConsecutive(int[] arr) {
        if (arr.length == 0) return 0;
        Arrays.sort(arr);
        int maxLen = 1, currLen = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1] + 1) {
                currLen++;
            } else if (arr[i] != arr[i - 1]) {
                currLen = 1;
            }
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }

    public boolean isCorrect(int n, int m, int[] hBars, int[] vBars, int output) {
        return maximizeSquareHoleArea(n, m, hBars, vBars) == output;
    }
}