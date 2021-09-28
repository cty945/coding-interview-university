import java.util.Arrays;

class Scratch {
    public static void main(String[] args) {
        Vector v = new Vector();
        v.push(1);
        v.push(2);
        v.push(3);
        v.push(2);
        v.push(1);
        v.push(1);
        v.push(4);
        v.push(4);
        v.push(4);
        System.out.println(v);
        System.out.println(v.size());

        System.out.println("Remove 1");
        v.remove(1);
        System.out.println(v);
        System.out.println("size:" + v.size());

        System.out.println("Insert 9 at 2");
        v.insert(2, 9);
        System.out.println(v);
        System.out.println(v.size());

        System.out.println("pop()");
        v.pop();
        System.out.println(v);
        System.out.println("size: " + v.size());

        int location = v.find(2);
        System.out.println("Location of 2:" + location);

        System.out.println("Delete 2");
        v.delete(2);
        System.out.println(v);
        System.out.println("size: " + v.size());
    }
}

class Vector {

    int capacity;
    int size;
    int[] arr;

    Vector() {
        this.capacity = 16;
        this.size = 0;
        this.arr = new int[this.capacity];
    }

    public String toString(){
        return Arrays.toString(this.arr);
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int at(int index) {
        return this.arr[index];
    }

    private void resize(int newCapacity) {
        int[] newArr = new int[newCapacity];
        for (int i = 0; i < this.size; i++) {
            newArr[i] = this.arr[i];
        }
        this.capacity = newCapacity;
        this.arr = newArr;
    }

    public void push(int item) {
        if (this.size == this.capacity) {
            this.resize(this.capacity * 2);
        }
        this.arr[this.size++] = item;
    }

    public void insert(int index, int item) {
        if (this.size == this.capacity) {
            this.resize(this.capacity * 2);
        }
        for (int i = size - 1; i >= index; i--) {
            this.arr[i + 1] = this.arr[i];
        }
        this.arr[index] = item;
        this.size++;
    }

    public void prepend(int item) {
        this.insert(0, item);
    }

    public int pop() {
        int item = this.arr[this.size - 1];
        this.arr[--this.size] = 0;

        if (this.size < this.capacity / 4) {
            this.resize(this.capacity / 2);
        }

        return item;
    }

    public void delete(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.arr[i] = this.arr[i + 1];
        }
        this.size--;
        if (this.size < this.capacity / 4) {
            this.resize(this.capacity / 2);
        }
    }

    public void remove(int item) {

        int count = 0;

        for (int i = 0; i < this.size; i++) {
            if (this.arr[i] == item) {
                count++;
            } else if (count > 0) {
                this.arr[i - count] = this.arr[i];
                this.arr[i] = 0;
            }
        }
        this.size -= count;
        if (this.size < this.capacity / 4) {
            this.resize(this.capacity / 2);
        }
    }

    public int find(int item) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i] == item){
                return i;
            }
        }
        return -1;
    }



}