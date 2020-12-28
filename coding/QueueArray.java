import java.util.Arrays;

class Scratch {
    public static void main(String[] args) throws Exception {


        QueueArray q = new QueueArray(5);

        System.out.println(q.empty());

        q.enqueue(1);
        System.out.println(q);

        int value = q.dequeue();
        System.out.println(q);
        System.out.println(value);


        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);

        System.out.println(q);

        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();

        System.out.println(q);
    }

    public static class QueueArray{

        int[] arr;

        int readIdx;
        int writeIdx;

        public QueueArray(int size){
            this.arr = new int[size + 1];
            this.readIdx = this.writeIdx = 0;
        }

        public void enqueue(int value) throws Exception{
            if ((this.writeIdx + 1) % this.arr.length == this.readIdx){
                throw new Exception("This queue has reached its maximum size");
            };
            this.arr[this.writeIdx] = value;
            this.writeIdx++;
        }

        public int dequeue() throws Exception{
            if (this.readIdx == this.writeIdx){
                throw new Exception("This queue has no element");
            }

            int val = this.arr[this.readIdx];
            this.arr[this.readIdx] = 0;
            this.readIdx = (this.readIdx + 1)%this.arr.length;
            return val;
        }

        public boolean empty(){
            return this.writeIdx == this.readIdx;
        }

        public boolean full(){
            return (this.writeIdx + 2) % this.arr.length == this.readIdx;
        }

        public String toString(){
            return Arrays.toString(this.arr);
        }

    }
}