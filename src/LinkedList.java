public class LinkedList {

    public static void main(String[] args) {
        ListNode testList = new ListNode(2, new ListNode(1));
        System.out.println(reverseSinglyLinkedList(testList));
    }

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * val: number
     * next: ListNode | null
     * constructor(val?: number, next?: ListNode | null) {
     * this.val = (val===undefined ? 0 : val)
     * this.next = (next===undefined ? null : next)
     * }
     * }
     */
    // Iterative approach
    private static ListNode reverseSinglyLinkedList(ListNode head) {
        if (null == head) {
            return null;
        }
        // Define variables for keeping track of nodes
        ListNode curr = head;
        ListNode prev = null;
        ListNode nextNode = null;

        // 1 - 2 - 3 - 4
        while (curr != null) {
            nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
