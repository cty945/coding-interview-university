class Scratch {
    public static void main(String[] args) {

        LinkedList l = new LinkedList();

        l.push_front(3);
        l.push_front(123);

        System.out.println(l);

        l.pop_back();
        System.out.println(l);

        l.pop_back();
        System.out.println(l);

        l.push_front(123);
        l.push_front(111);
        l.push_front(222);
        System.out.println(l);

        System.out.println("front: " + l.front());
        System.out.println("back: " + l.back());

        l.insert(1, 666);
        System.out.println(l);

        l.erase(1);
        System.out.println(l);

        int oneFromEnd = l.value_n_from_end(2);
        System.out.println(oneFromEnd);

        l.reverse();
        System.out.println(l);

        l.remove_value(111);
        System.out.println(l);



    }



    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
    }

    static class LinkedList {

        ListNode head;

        int size;

        public LinkedList(){}

        public int size(){
            return this.size;
        }

        public boolean empty(){
            return this.size == 0;
        }

        public int value_at(int index){

            ListNode cur = this.head;

            for (int i=0; i<index; i++){
                cur = cur.next;
            }

            return cur.val;
        }

        public void push_front(int value){
            ListNode temp = new ListNode(value);
            temp.next = this.head;
            this.head = temp;
            this.size++;
        }

        public int pop_front(int value){
            int temp = this.head.val;
            this.head = this.head.next;
            this.size--;
            return temp;
        }

        public void push_back(int value){
            if (this.head == null){
                this.head = new ListNode(value);
            } else {
                ListNode cur = head;
                while(cur.next != null){
                    cur = cur.next;
                }
                cur.next = new ListNode(value);
            }
            this.size++;
        }

        public int pop_back(){

            // only 1 node case
            if (this.head.next == null){
                int temp = this.head.val;
                this.head = null;
                this.size--;
                return temp;
            } else { //more than 1 node case
                ListNode cur = head;
                while (cur.next.next != null){
                    cur = cur.next;
                }
                int temp = cur.next.val;
                cur.next = null;
                this.size--;
                return temp;
            }
        }

        public int front(){
            return this.head.val;
        }

        public int back(){
            ListNode cur = this.head;
            while(cur.next != null){
                cur = cur.next;
            };
            return cur.val;
        }

        public void insert(int index, int value){

            if (this.head == null && index == 0){
                this.head = new ListNode(value);
            } else{

                ListNode cur = this.head;

                while (index > 1){
                    cur = cur.next;
                    index--;
                }

                ListNode temp = new ListNode(value);
                temp.next = cur.next;
                cur.next = temp;
            }

            this.size++;
        }

        public void erase(int index){

            if (index == 0){
                this.head = this.head.next;
            } else {
                ListNode cur = this.head;
                while(index > 1){
                    cur = cur.next;
                    index--;
                }
                cur.next = cur.next.next;
            }

            this.size--;
        }

        public int value_n_from_end(int n){

            ListNode fast = this.head;
            ListNode slow = this.head;

            while (n > 0){
                fast = fast.next;
                n--;
            }

            while (fast.next != null){
                fast = fast.next;
                slow = slow.next;
            }

            return slow.val;
        }

        public void reverse(){

            ListNode prev = null;
            ListNode cur = this.head;

            while(cur != null){
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            this.head = prev;
        }

        public void remove_value(int value){

            if (this.head.val == value){
                this.head = this.head.next;
                this.size--;
                return;
            }

            ListNode cur = this.head;
            while(cur.next != null){
                if (cur.next.val == value){
                    cur.next = cur.next.next;
                    this.size--;
                    return;
                }
            }

        }

        public String toString(){

            String result = "";

            ListNode cur = this.head;
            while (cur != null){
                result = result.concat(cur.val + " => ");
                cur = cur.next;
            }
            result = result.concat("null");
            return result;
        }


    }
}