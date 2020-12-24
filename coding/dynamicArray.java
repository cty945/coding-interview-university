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

        v.remove(1);
        System.out.println(v);
        System.out.println(v.size());

        v.insert(2, 9);
        System.out.println(v);
        System.out.println(v.size());

        v.pop();
        System.out.println(v);
        System.out.println(v.size());

        int location = v.find(2);
        System.out.println(location);

        v.delete(2);
        System.out.println(v);
        System.out.println(v.size());
    }


    static class Vector {

        int size = 0;
        int capacity;
        int array[];

        public Vector() {
            this.capacity = 16;
            this.array = new int[this.capacity];
        }

        public int size(){
            return this.size;
        }

        public int capacity(){
            return this.capacity;
        }

        public boolean is_empty(){
            return this.size == 0;
        }

        public int at(int index){
            return this.array[index];
        }

        public void push(int item){
            if (this.size == this.capacity){
                this.resize(this.capacity * 2);
            }
            this.array[size] = item;
            this.size++;
        }

        public void insert(int index, int item){
            if (this.size == this.capacity){
                this.resize(this.capacity * 2);
            }
            for (int i=this.size; i>index; i--){
                this.array[i] = this.array[i-1];
            }
            this.array[index] = item;
            this.size++;
        }

        public void prepend(int item){
            this.insert(0, item);
        }

        public int pop(){
            this.size--;
            int temp = this.array[size];
            this.array[size] = 0;
            return temp;
        }

        public void delete(int index){
            for (int i=index; i<size-1; i++){
                this.array[i] = this.array[i+1];
            }
            this.array[this.size-1] = 0;
            this.size--;
        }

        public void remove(int item){
            int count = 0;
            for (int i=0; i<this.size; i++){
                if (this.array[i] == item){
                    this.array[i] = 0;
                    count++;
                } else {
                    if (count > 0){
                        this.array[i - count] = this.array[i];
                        this.array[i] = 0;
                    }
                }
            }
            this.size -= count;
        }

        public int find(int item){
            for (int i=0; i<this.size;i++){
                if (this.array[i] == item){
                    return i;
                }
            }
            return -1;
        }

        private void resize(int capacity){
            this.capacity = capacity;
            int newArr[] = new int[this.capacity];
            for (int i=0; i<this.size; i++){
                newArr[i] = this.array[i];
            }
            this.array = newArr;
        }

        public String toString(){
            return Arrays.toString(this.array);
        }
    }

}