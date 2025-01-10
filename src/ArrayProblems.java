import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrayProblems {

    public static void main(String[] args) {
        ArrayProblems solutions = new ArrayProblems();
        int[] initialArray = {1, 2, 3, 4};
        int[] productArray = solutions.productExceptSelf(initialArray);
        System.out.println(Arrays.stream(productArray).boxed().collect(Collectors.toList()));

    }

    public int[] productExceptSelf(int[] nums) {
        int[] answers = new int[nums.length];
        int preFix = 1;
        int postFix = 1;

        // Left to right calculation
        for (int i = 0; i < nums.length; i++) {
            answers[i] = preFix;
            preFix *= nums[i];
        }

        // Right to left calculation
        for (int i = nums.length - 2; i >= 0; i--) {
            postFix *= nums[i + 1];
            answers[i] *= postFix;
        }

        return answers;
    }

    // String compression
    public int compress(char[] chars) {
        int writeIndex = 0;
        int currentCharCount = 1;
        char currentLetter = chars[0];

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == currentLetter) {
                currentCharCount++;
            } else {
                chars[writeIndex++] = currentLetter;
                if (currentCharCount > 1) {
                    for (char c : Integer.toString(currentCharCount).toCharArray()) {
                        chars[writeIndex++] = c;
                    }
                }
                currentLetter = chars[i];
                currentCharCount = 1;
            }
        }

        chars[writeIndex++] = currentLetter;
        if (currentCharCount > 1) {
            for (char c : Integer.toString(currentCharCount).toCharArray()) {
                chars[writeIndex++] = c;
            }
        }

        return writeIndex;
    }
}
