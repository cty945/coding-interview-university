class Scratch {
    public static void main(String[] args) {

        QueueList q = new QueueList();

        System.out.println(q.empty());

        q.enqueue(1);
        System.out.println(q);

        q.dequeue();
        System.out.println(q);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println(q);

        q.dequeue();
        System.out.println(q);

    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
    }

    public static class QueueList{

        ListNode head;
        ListNode tail;

        public QueueList(){
            this.head = this.tail = new ListNode(-1); //dummy node
        }

        public void enqueue(int value){
            ListNode temp = new ListNode(value);
            this.tail.next = temp;
            this.tail = temp;
        };

        public int dequeue(){
            int result = this.head.val;
            this.head = this.head.next;
            return result;
        }

        public boolean empty(){
            return this.head == this.tail;
        }

        public String toString(){

            String result = "";

            ListNode cur = this.head.next;
            while (cur != null){
                result = result.concat(cur.val + " => ");
                cur = cur.next;
            }
            result = result.concat("null");
            return result;
        }
    }
}