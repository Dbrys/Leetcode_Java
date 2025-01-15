import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Heaps {

    public static void main(String[] args) {

    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            maxHeap.add(num);
        }

        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }
        return maxHeap.peek();
    }

    public long maxScore(int[] nums1, int[] nums2, int k) {
        int numsLength = nums1.length;

        int[][] pairs = new int[numsLength][2];
        for (int i = 0; i < pairs.length; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
        }
        // Sort pairs in descending order of nums2
        Arrays.sort(pairs, (a, b) -> Integer.compare(b[1], a[1]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Now we need to loop through pairs of k length to find max
        long score = 0;
        long curSum = 0;
        for (int i = 0; i < pairs.length; i++) {
            int num1 = pairs[i][0];
            int num2 = pairs[i][1];

            minHeap.add(num1);
            curSum += num1;

            if (minHeap.size() > k) {
                curSum -= minHeap.remove();
            }
            if (minHeap.size() == k) {
                score = Math.max(score, curSum * num2);
            }

        }
        return score;
}
