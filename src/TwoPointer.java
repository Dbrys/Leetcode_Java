public class TwoPointer {

    public static void main(String[] args) {
        TwoPointer twoPointer = new TwoPointer();
        System.out.println(twoPointer.isSubsequence("abc", "avgbrc"));
    }

    public boolean isSubsequence(String s, String t) {
        int sPointer = 0;

        for (int i = 0; i < t.length(); i++) {
            if (sPointer < s.length() && s.charAt(sPointer) == t.charAt(i)) {
                sPointer++;
            }
        }
        return sPointer == s.length();
    }

    public void moveZeroes(int[] nums) {
        int pointer = 0;

        for (int i = 0; i < nums.length; i++) {
            int curVal = nums[i];
            nums[i] = 0;
            if (curVal != 0) {
                nums[pointer] = curVal;
                pointer++;
            }
        }
    }
}
