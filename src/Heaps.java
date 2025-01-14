import java.util.PriorityQueue;

public class Heaps {

    public static void main(String[] args) {

    }

    private Integer minNum = 1;
    private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>()

    public int popSmallest() {
        int result = minNum;

        if (!this.minHeap.isEmpty() && this.minHeap.peek() < minNum) {
            result = this.minHeap.poll();
        } else {
            minNum++;
        }
        while (!this.minHeap.isEmpty() && this.minHeap.peek() == result) {
            this.minHeap.poll();
        }
        return result;
    }

    public void addBack(int num) {
        this.minHeap.add(num);
    }
}
