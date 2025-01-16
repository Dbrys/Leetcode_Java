import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] spells = {3, 1, 2};
        int[] potions = {8, 5, 8};
        System.out.println(Arrays.toString(spellsAndPotions(spells, potions, 16)));
    }

    // Spells and potions
    public static int[] spellsAndPotions(int[] spells, int[] potions, long success) {
        int[] successCountList = new int[spells.length];
        Arrays.sort(potions);

        for (int i = 0; i < spells.length; i++) {
            int left = 0;
            int right = potions.length - 1;
            int successCount = 0;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if ((long) potions[mid] * spells[i] >= success) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            successCount = potions.length - left;
            successCountList[i] = successCount;
        }
        return successCountList;
    }

    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] > nums[middle + 1]) {
                right = middle;
            } else {
                left = middle + 1;
            }

        }
        return left;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();

        while (left <= right) {
            int mid = (left + right) / 2;
            long totalHours = 0;

            for (int pile : piles) {
                totalHours += (long) (pile + mid - 1) / mid;
            }

            if (totalHours > h) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;

    }
}

