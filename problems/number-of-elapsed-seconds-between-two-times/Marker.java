import java.util.*;

class Marker {
    public int secondsBetweenTimes(String startTime, String endTime) {
        return secs(endTime) - secs(startTime);
    }

    private int secs(String t) {
        return Integer.parseInt(t.substring(0, 2)) * 3600
             + Integer.parseInt(t.substring(3, 5)) * 60
             + Integer.parseInt(t.substring(6, 8));
    }

    public boolean isCorrect(String startTime, String endTime, int output) {
        return output == secondsBetweenTimes(startTime, endTime);
    }
}
