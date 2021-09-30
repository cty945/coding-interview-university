class Scratch {
    public static void main(String[] args) throws Exception {

        LinkedList l = new LinkedList();

        l.push_front(1);
        l.push_front(2);

        System.out.println("beginning: " + l);

        System.out.println("pop back,");
        l.pop_back();
        System.out.println(l);
        System.out.println("size:" + l.size());

        System.out.println("pop back,");
        l.pop_back();
        System.out.println(l);
        System.out.println("size:" + l.size());

        l.push_front(3);
        l.push_front(4);
        l.push_front(5);
        System.out.println(l);
        System.out.println("size:" + l.size());

        System.out.println("front: " + l.front());
        System.out.println("back: " + l.back());
        System.out.println("size:" + l.size());

        System.out.println("pop back");
        l.pop_back();
        System.out.println(l);
        System.out.println("size:" + l.size());

        System.out.println("pop front");
        l.pop_front();
        System.out.println(l);
        System.out.println("size:" + l.size());

        System.out.println("insert: index 0, value 666");
        l.insert(0, 666);
        System.out.println(l);
        System.out.println("size:" + l.size());

        System.out.println("erase 1");
        l.erase(0);
        System.out.println(l);
        System.out.println("size:" + l.size());

        l.push_front(3);
        l.push_front(1);
        l.push_front(5);
        System.out.println(l);
        System.out.println("size:" + l.size());

        int n = 0;
        int temp = l.value_n_from_end(n);
        System.out.println(n + " from end:" + temp);

        System.out.println(l);
        System.out.println("reverse");
        l.reverse();
        System.out.println(l);

        System.out.println("remove 5");
        l.remove_value(5);
        System.out.println(l);
    }
}


class ListNode {
    ListNode next;
    int val;
    ListNode(int val) {
        this.val = val;
    }
}

class LinkedList {

    ListNode head;
    ListNode tail;
    int size;

    LinkedList() {
        this.size = 0;
    }

    int size() {
        return this.size;
    }

    boolean empty() {
        return this.size == 0;
    }

    int value_at(int index) {
        ListNode cur = this.head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    void push_front(int value) {
        ListNode temp = new ListNode(value);
        temp.next = this.head;
        this.head = temp;
        if (this.tail == null) {
            this.tail = this.head;
        }
        this.size++;
    }

    int pop_front() throws Exception {
        if (this.size == 0) {
            throw new Exception("Nothing to pop");
        } else {
            int result = this.head.val;
            this.head = this.head.next;
            this.size--;
            return result;
        }
    }

    int pop_back() throws Exception {
        int result;
        if (this.size == 0) {
            throw new Exception("Nothing to pop");
        } else if (this.size == 1) {
            result = this.head.val;
            this.head = this.tail = null;
        } else {
            ListNode cur = this.head;
            while (cur.next.next != null) {
                cur = cur.next;
            }
            result = cur.next.val;
            cur.next = null;
            this.tail = cur.next;
        }
        this.size--;
        return result;
    }

    int front() {
        return this.head.val;
    }

    int back() {
        return this.tail.val;
    }

    void insert(int index, int value) {
        ListNode temp = new ListNode(value);
        ListNode cur = this.head;
        if (index == 0) {
            this.push_front(value);
        } else {
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            temp.next = cur.next;
            cur.next = temp;
        }
    }

    void erase(int index) throws Exception {
        int result;

        ListNode cur = this.head;
        if (index == 0) {
            this.pop_front();
        } else {
            for (int i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            this.size--;
        }
    }

    int value_n_from_end(int n) {
        ListNode slow = this.head;
        ListNode fast = this.head;
        while (n > 0) {
            fast = fast.next;
            n--;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.val;
    }

    private void reverse_iterative() {

        ListNode cur = this.head;
        ListNode prev = null;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        this.head = prev;
    }

    private ListNode reverse_helper(ListNode prev, ListNode cur) {

        if (cur == null) {
            return prev;
        }

        ListNode next = cur.next;
        cur.next = prev;
        return reverse_helper(cur, next);
    }

    private void reverse_recursive() {
        this.head = this.reverse_helper(null, this.head);
    }


    void reverse() {
        if (this.size == 1) return;
//        this.reverse_iterative();
        this.reverse_recursive();
    }

    void remove_value(int value) {

        if (this.size == 1) {
            if (this.head.val == value){
                this.head = this.tail = null;
            }
        } else {
            if (this.head.val == value){
                this.head = this.head.next;
                if (this.head == null) this.tail = null;
            } else {
                ListNode cur = this.head;
                while (cur.next.val != value) {
                    cur = cur.next;
                }
                cur.next = cur.next.next;
            }

        }

    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        ListNode cur = this.head;
        while (cur != null) {
            result.append(cur.val).append(" => ");
            cur = cur.next;
        }
        ;
        result.append("null");
        return result.toString();
    }
}