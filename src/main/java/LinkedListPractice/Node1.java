package LinkedListPractice;

public class Node1 {
    int data;
    Node1 next = null;

    Node1(final int data) {
        this.data = data;
    }

    public static Node1 push(final Node1 head, final int data) {

        Node1 newHead = new Node1(data);
        newHead.next = head;

        return newHead;
    }

    public static Node1 buildOneTwoThree() {
        Node1 chained = null;

        for (int i = 3; i > 0; i--) {

            chained = push(chained, i);
        }

        return chained;
    }
}
