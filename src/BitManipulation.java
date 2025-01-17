import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BitManipulation {

    public static void main(String[] args) {
        BitManipulation bitManipulation = new BitManipulation();
        int[] bitArray = bitManipulation.countBits(10);
        ArrayList<Integer> countBitList = Arrays.stream(bitArray).boxed().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(countBitList);

    }

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}
