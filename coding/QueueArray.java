import java.util.Arrays;

class Scratch {
    public static void main(String[] args) throws Exception {
        QueueArray queue = new QueueArray(3);

        System.out.println("enqueue 1,2,3");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);

        System.out.println("dequeue");
        int val = queue.dequeue();
        System.out.println(val);
        System.out.println(queue);

        System.out.println("dequeue");
        int val2 = queue.dequeue();
        System.out.println(val2);
        System.out.println(queue);

        System.out.println("dequeue");
        int val3 = queue.dequeue();
        System.out.println(val3);
        System.out.println(queue);

        System.out.println("enqueue 1,2,3");
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
    }
}

class QueueArray {

    int capacity;
    int readIdx = 0;
    int writeIdx = 0;
    int[] arr;

    QueueArray(int size) {
        this.capacity = size + 1;
        this.arr = new int[size + 1];

    }

    public void enqueue(int value) throws Exception {
        if (this.full()) {
            throw new Exception("Maximum size exceeded");
        }
        this.arr[this.writeIdx] = value;
        this.writeIdx = (this.writeIdx + 1) % this.capacity;
    }

    public int dequeue() throws Exception {
        if (this.readIdx == this.writeIdx){
            throw new Exception("Queue is empty");
        }
        int res = this.arr[this.readIdx];
        this.arr[this.readIdx] = 0;
        this.readIdx = (this.readIdx + 1) % this.capacity;
        return res;
    }

    public boolean empty() {
        return this.readIdx == this.writeIdx;
    }

    public boolean full() {
        return (this.writeIdx + 1) % this.capacity == this.readIdx;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.arr);
    }

}