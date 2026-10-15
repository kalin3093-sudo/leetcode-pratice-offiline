import java.util.*;
class Marker {
    public int[] largestPower(int[] nums) {
        int n = nums.length;
        List<Integer> order = new ArrayList<>();
        for (int x : nums) order.add(x);
        
        // Groups represented as ranges in order list
        List<int[]> groups = new ArrayList<>();
        groups.add(new int[]{0, n - 1});
        
        int[] power = new int[15];
        for (int bit = 14; bit >= 0; bit--) {
            int idx = 14 - bit;
            int count = 0;
            List<int[]> newGroups = new ArrayList<>();
            boolean splitDone = false;
            
            for (int[] g : groups) {
                int l = g[0], r = g[1];
                // Count how many in this group have the bit set
                int withBit = 0;
                for (int i = l; i <= r; i++) {
                    if ((order.get(i) & (1 << bit)) != 0) withBit++;
                }
                if (withBit == r - l + 1) {
                    // All have the bit
                    count += withBit;
                    newGroups.add(g);
                } else if (withBit == 0) {
                    // None have the bit
                    newGroups.add(g);
                } else if (!splitDone) {
                    // First mixed group: partition by bit
                    // Move elements with bit to front of this group
                    List<Integer> withB = new ArrayList<>();
                    List<Integer> withoutB = new ArrayList<>();
                    for (int i = l; i <= r; i++) {
                        int val = order.get(i);
                        if ((val & (1 << bit)) != 0) withB.add(val);
                        else withoutB.add(val);
                    }
                    int pos = l;
                    for (int v : withB) order.set(pos++, v);
                    for (int v : withoutB) order.set(pos++, v);
                    
                    count += withB.size();
                    if (!withB.isEmpty()) newGroups.add(new int[]{l, l + withB.size() - 1});
                    if (!withoutB.isEmpty()) newGroups.add(new int[]{l + withB.size(), r});
                    splitDone = true;
                } else {
                    // Already split, keep group as is
                    newGroups.add(g);
                }
            }
            power[idx] = count;
            groups = newGroups;
        }
        return power;
    }

    public boolean isCorrect(int[] nums, int[] output) {
        if (output == null) return false;
        int[] expected = largestPower(nums);
        return Arrays.equals(expected, output);
    }
}