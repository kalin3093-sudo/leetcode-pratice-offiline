import java.util.*;

class Marker {
    public int maximumGap(String skill, String station) {
        int n = skill.length();
        int m = station.length();
        int[] earliest = new int[n];
        int[] latest = new int[n];
        
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (station.charAt(j) != skill.charAt(i)) j++;
            earliest[i] = j++;
        }
        
        j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (station.charAt(j) != skill.charAt(i)) j--;
            latest[i] = j--;
        }
        
        int ans = 0;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, latest[i] - earliest[i - 1]);
        }
        return ans;
    }

    public boolean isCorrect(String skill, String station, int output) {
        return output == maximumGap(skill, station);
    }
}