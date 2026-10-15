public class Marker {

    public int countSpecialIntegers(int[] nums) {
        int cnt = 0;
        for (int x = 1; x <= 100; x++) {
            int first = -1, last = -1, occ = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == x) {
                    if (first < 0) first = i;
                    last = i;
                    occ++;
                }
            }
            if (occ > 0 && last - first + 1 == occ) cnt++;
        }
        return cnt;
    }

    public boolean isCorrect(int[] nums, int out) {
        return countSpecialIntegers(nums) == out;
    }
}
