package LinkedListPractice;

public class LengthAndCount<T> {

    private class Node<T> {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int length(Node<T> head) {
        int links = 0;
        Node<T> current = head;

        while (current.next != null) {
            current = current.next;
            links++;
        }
        return links;
    }

    private int count(Node<T> head, T data) {
        int dataCount = 0;
        Node<T> current = head;

        while (current.next != null) {
            if (current.data == data) {
                dataCount++;
            }
            current = current.next;
        }

        return dataCount;
    }

    public static void main(String[] args){
        LengthAndCount<Integer> list = new LengthAndCount<>();
    }
}
