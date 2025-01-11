import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public String reverseVowels(String s) {
        Set<Character> vowelsSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        int leftPointer = 0;
        int rightPointer = s.length() - 1;
        char[] stringAsList = s.toCharArray();

        while (leftPointer < rightPointer) {
            if (vowelsSet.contains(Character.toLowerCase(stringAsList[leftPointer]))
                    && vowelsSet.contains(Character.toLowerCase(stringAsList[rightPointer]))) {
                char leftVowel = stringAsList[leftPointer];
                stringAsList[leftPointer] = stringAsList[rightPointer];
                stringAsList[rightPointer] = leftVowel;
                leftPointer++;
                rightPointer--;
            } else if (vowelsSet.contains(Character.toLowerCase(stringAsList[leftPointer]))
                    && !vowelsSet.contains(Character.toLowerCase(stringAsList[rightPointer]))) {
                rightPointer--;
            } else if (!vowelsSet.contains(Character.toLowerCase(stringAsList[leftPointer]))
                    && vowelsSet.contains(Character.toLowerCase(stringAsList[rightPointer]))) {
                leftPointer++;
            } else {
                leftPointer++;
                rightPointer--;
            }
        }
        return new String(stringAsList);
    }

    public String reverseWords(String s) {
        String stringTrimmed = s.trim();
        List<String> stringList = Arrays.stream(stringTrimmed.split(" ")).filter(word -> !word.isEmpty())
                .collect(Collectors.toList());

        int left = 0;
        int right = stringList.size() - 1;

        while (left < right) {
            if (!stringList.get(left).isEmpty() && !stringList.get(right).isEmpty()) {
                String leftString = stringList.get(left);
                stringList.set(left, stringList.get(right));
                stringList.set(right, leftString);
                left++;
                right--;
            }
        }
        return String.join(" ", stringList);
    }

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int numOfOperations = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];
            System.out.println(left);
            System.out.println(right);
            if (sum == k) {
                numOfOperations++;
                left++;
                right--;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }

        }

        return numOfOperations;
    }

    // Container most water
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxAmount = 0;

        while (left < right) {
            int distance = right - left;
            int containerAmount = Math.min(height[left], height[right]) * distance;
            if (containerAmount > maxAmount) {
                maxAmount = containerAmount;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxAmount;
    }
}
