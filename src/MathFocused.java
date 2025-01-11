public class MathFocused {

    public static void main(String[] args) {
        //TEST
        System.out.println(reverseNumber(9894));
        System.out.println(reverseNumber(-123));
        System.out.println(reverseNumber(2147483647));
    }

    private static int reverseNumber(int num) {
        int reverse = 0;
        int sign = num < 0 ? -1 : 1;
        num = Math.abs(num);

        while (num > 0) {
            int lastDigit = num % 10;

            if (reverse > (Integer.MAX_VALUE - lastDigit) / 10) {
                return 0;
            }
            reverse = (reverse * 10) + lastDigit;
            num = num / 10;
        }
        return reverse * sign;
    }


    public int largestAltitude(int[] gain) {
        int maxAltitude = gain[0];
        int runningTotal = gain[0];

        for (int i = 1; i < gain.length; i++) {
            runningTotal += gain[i];
            maxAltitude = Math.max(maxAltitude, runningTotal);
        }
        return Math.max(maxAltitude, 0);
    }
}
