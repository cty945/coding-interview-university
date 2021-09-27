import java.util.List;

class Scratch {
    public static void main(String[] args) throws Exception {

        LinkedList l = new LinkedList();

        l.push_front(1);
        l.push_front(2);

        System.out.println("beginning: " + l);

        System.out.println("pop back,");
        l.pop_back();
        System.out.println(l);

        l.pop_back();
        System.out.println(l);

        l.push_front(3);
        l.push_front(4);
        l.push_front(5);
        System.out.println(l);

        System.out.println("front: " + l.front());
        System.out.println("back: " + l.back());

        System.out.println("pop back");
        l.pop_back();
        System.out.println(l);

        System.out.println("pop front");
        l.pop_front();
        System.out.println(l);

        System.out.println("insert: index 0, value 666");
        l.insert(0, 666);
        System.out.println(l);

        System.out.println("erase: index 1");
        l.erase(1);
        System.out.println(l);

        System.out.println("push fron 5");
        l.push_front(5);
        System.out.println(l);

        int n = 1;
        int temp = l.value_n_from_end(n);
        System.out.println(n + " from end:" + temp);

//        System.out.println("pop front");
//        l.pop_front();
//        System.out.println(l);
//
//        System.out.println("pop front");
//        l.pop_front();
//        System.out.println(l);

        System.out.println("reverse");
        l.reverse();
        System.out.println(l);

        l.head.next.next = new ListNode(4);
        System.out.println(l);

        System.out.println("remove 4");
        l.remove_value(4);
        System.out.println(l);


    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    static class LinkedList {

        ListNode head;

        int size;

        public LinkedList() {
        }

        public int size() {
            return this.size;
        }

        public boolean empty() {
            return this.size == 0;
        }

        public int value_at(int index) {

            ListNode cur = this.head;

            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }

            return cur.val;
        }

        public void push_front(int value) {
            ListNode temp = new ListNode(value);
            temp.next = this.head;
            this.head = temp;
            this.size++;
        }

        public int pop_front() {
            int temp = this.head.val;
            this.head = this.head.next;
            this.size--;
            return temp;
        }


        public void push_back(int value) {
            if (this.head == null) {
                this.head = new ListNode(value);
            } else {
                ListNode cur = head;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = new ListNode(value);
            }
            this.size++;
        }

        //assume the linkedlist has at least 1 item
        public int pop_back() {
            int temp;
            if (this.size == 1) {
                temp = this.head.val;
                this.head = null;
            } else {
                ListNode cur = this.head;
                while (cur.next.next != null) {
                    cur = cur.next;
                }
                temp = cur.next.val;
                cur.next = null;
            }
            this.size--;
            return temp;
        }


        public int front() {
            return this.head.val;
        }

        public int back() {
            ListNode cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            return cur.val;
        }

        public void insert(int index, int value) {
            ListNode temp = new ListNode(value);
            if (index == 0) {
                temp.next = this.head;
                this.head = temp;
            } else {
                ListNode cur = this.head;
                while (index > 1) {
                    cur = cur.next;
                    index--;
                }
                temp.next = cur.next;
                cur.next = temp;
            }
            this.size++;
        }

        public void erase(int index) throws Exception {

            if (index > this.size - 1) {
                throw new Exception("index out of bound!");
            }

            if (index == 0) {
                this.head = this.head.next;
            } else {
                ListNode cur = this.head;
                while (index > 1) {
                    cur = cur.next;
                    index--;
                }
                cur.next = cur.next.next;
            }
            this.size--;
        }

        public int value_n_from_end(int n) throws Exception {

            if (n > this.size - 1) {
                throw new Exception("index out of bound!");
            }

            ListNode slow = this.head;
            ListNode fast = this.head;
            while (n > 0) {
                fast = fast.next;
                n--;
            }
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow.val;
        }

        public void reverse() {
//            this.iterative_reverse();
            this.recursive_reverse();
        }


        private void recursive_reverse() {
            //fix the head pointer
//            this.head = this.rev_nontail(this.head);
            this.head = this.rev_tail(null, this.head);
        }


        /**
         * Non-tail recursive reverse. At each iteration, link cur.next.next => cur.
         * Changing 1 pointer at each recursion, from Tail to head.
         *
         * @return Return the last node is the original Linked List
         */
        private ListNode rev_nontail(ListNode cur) {

            // handle empty list case
            if (cur == null || cur.next == null) { // when we reached the last node
                return cur;
            }
            ListNode last = this.rev_nontail(cur.next);
            cur.next.next = cur;

            cur.next = null;

            return last;
        }

        /**
         * Tail recursive reverse. Node that this is just a recursive version of the iterative method.
         * At each iteration, link cur => prev. Changing 1 pointer at each recursion, from head to tail.
         *
         * @param prev this pointer point to previous node
         * @param cur  this pointer point to current node
         * @return the last node in the original list
         */
//        private ListNode rev_tail(ListNode prev, ListNode cur) {
//
//            if (cur == null) {
//                return prev;
//            }
//
//            ListNode next = cur.next;
//            cur.next = prev;
//
//            return this.rev_tail(cur, next);
//        }

        private ListNode rev_tail(ListNode prev, ListNode cur) {

            if (cur == null){
                return prev;
            }

            ListNode next = cur.next;
            cur.next = prev;

            return this.rev_tail(cur, next);
        }


        /**
         *
         */
        private void iterative_reverse() {
            ListNode prev = null, cur, next;

            cur = this.head;
            while (cur != null) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            this.head = prev;
        }
//
//        public void remove_value(int value) {
//
//            if (this.head.val == value) {
//                this.head = this.head.next;
//                this.size--;
//                return;
//            }
//
//            ListNode cur = this.head;
//            while (cur.next != null) {
//                if (cur.next.val == value) {
//                    cur.next = cur.next.next;
//                    this.size--;
//                    return;
//                }
//                cur = cur.next;
//            }
//
//        }

        /**
         * this function removes the first node of val value;
         *
         * @param value
         */
        public void remove_value(int value) {
            if (this.head == null) {
                return;
            } else if (this.head.val == value) {
                this.head = this.head.next;
                this.size--;
            } else {
                ListNode cur = this.head;
                while (cur.next != null && cur.next.val != value) {
                    cur = cur.next;
                }
                if (cur.next != null) {
                    cur.next = cur.next.next;
                    this.size--;
                }
            }
        }

        public String toString() {

            String result = "";

            ListNode cur = this.head;
            while (cur != null) {
                result = result.concat(cur.val + " => ");
                cur = cur.next;
            }
            result = result.concat("null");
            return result;
        }


    }
}