import java.util.*;

class Marker {
    public int numWaterBottles(int numBottles, int numExchange) {
        int drunk = 0, empty = 0;
        while (numBottles > 0) {
            drunk += numBottles;
            empty += numBottles;
            numBottles = empty / numExchange;
            empty %= numExchange;
        }
        return drunk;
    }

    public boolean isCorrect(int numBottles, int numExchange, int output) {
        return output == numWaterBottles(numBottles, numExchange);
    }
}
