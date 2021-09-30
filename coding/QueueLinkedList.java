class Scratch {
    public static void main(String[] args) {

        QueueLinkedList queue = new QueueLinkedList();

        System.out.println("enqueue 1,2,3");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("dequeue");
        int val = queue.dequeue();
        System.out.println(val);

        System.out.println("dequeue");
        int val2 = queue.dequeue();
        System.out.println(val2);

        System.out.println("dequeue");
        int val3 = queue.dequeue();
        System.out.println(val3);

    }
}


class ListNode {
    ListNode next;
    int val;

    ListNode(int val) {
        this.val = val;
    }
}


class QueueLinkedList {

    ListNode head;
    ListNode tail;

    QueueLinkedList() {
    }

    public void enqueue(int value) {
        this.pushbask(value);
    }

    private void pushbask(int value) {
        ListNode temp = new ListNode(value);
        if (this.tail != null) {
            this.tail.next = temp;
        }
        this.tail = temp;
        if (this.head == null) {
            this.head = temp;
        }
    }

    public int dequeue() {
        int res = this.head.val;
        this.head = this.head.next;
        if (this.head == null){
            this.tail = null;
        }
        return res;
    }

    public boolean empty() {
        return this.head == null;
    }

}