import java.util.*;

class Marker {
    public String trafficSignal(int timer) {
        if (timer == 0) return "Green";
        if (timer == 30) return "Orange";
        if (timer > 30 && timer <= 90) return "Red";
        return "Invalid";
    }

    public boolean isCorrect(int timer, String output) {
        return output != null && output.equals(trafficSignal(timer));
    }
}
