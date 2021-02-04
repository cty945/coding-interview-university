import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Scratch {
    public static void main(String[] args) {

//        MaxHeap heap = new MaxHeap<String>();
//        heap.inserts(3, "abc");
//        heap.inserts(5, "555");
//        heap.inserts(6, "666");
//        heap.inserts(1, "111");
//        System.out.println(heap);
//        Node extracted = heap.extract_max();
//        System.out.println(extracted);
//        System.out.println(heap);
//
//        heap.remove(2);
//        System.out.println(heap);


        Node[] arr = new Node[]{new Node(5, "555"), new Node(3, "abc"),  new Node(6, "666"), new Node(9, "999"), new Node(1, "111")};

        MaxHeap heap = new MaxHeap<String>();
        List sorted = heap.heap_sort(arr);
        System.out.println("sorted: \n" + sorted);
    }


    public static class Node<T>{
        int key;
        T value;

        Node(int key, T value){
            this.key = key;
            this.value = value;
        }

        public String toString(){
            return String.format("(%d, %s)", this.key, this.value);
        }

    };

    public static class MaxHeap<T>{

        List<Node> tree;
        int size = 0;

        MaxHeap(){
            this.tree = new ArrayList<Node>();
            this.tree.add(new Node(-1, "dummy"));
        };

        public void inserts(int key, T value){
            this.tree.add(new Node(key, value));
            this.size++;
            this.sift_up(this.size);
        }

        public void sift_up(int idx){
            while(idx > 1 && this.tree.get(idx/2).key < this.tree.get(idx).key){
                Node temp = this.tree.get(idx/2);
                this.tree.set(idx/2, this.tree.get(idx));
                this.tree.set(idx, temp);
                idx = idx/2;
            }
        }


        public void sift_down(int idx){

            int maxIdx = idx;
            int leftIdx = idx*2;
            int rightIdx = idx*2+1;
            if (leftIdx <= this.size && this.tree.get(leftIdx).key > this.tree.get(maxIdx).key){
                maxIdx = leftIdx;
            }
            if (rightIdx <= this.size && this.tree.get(rightIdx).key > this.tree.get(maxIdx).key){
                maxIdx = rightIdx;
            }
            if (maxIdx != idx){
                Node temp = this.tree.get(idx);
                this.tree.set(idx, this.tree.get(maxIdx));
                this.tree.set(maxIdx, temp);
                this.sift_down(maxIdx);
            }
        }


        public int get_size(){
            return this.size;
        }

        public boolean is_empty(){
            return this.size == 0;
        }

        public Node extract_max(){
            Node temp = this.tree.get(1);
            this.tree.set(1, this.tree.get(this.size));
            this.size--;
            this.sift_down(1);
            return temp;
        }

        public void remove(int i){
            Node temp = this.tree.get(i);
            this.tree.set(i, this.tree.get(this.size));
            this.tree.set(this.size, null);
            this.size--;
            this.sift_down(i);
        }

        private void heapify(Node[] arr){
            this.tree = new ArrayList<>();
            this.tree.add(0, new Node(-1, "dummy"));
            Collections.addAll(this.tree, arr);
            this.size = arr.length;
            for (int i=this.size/2; i>=1; i--){
                this.sift_down(i);
            }
        }

        public List<Node> heap_sort(Node[] arr) {
            this.heapify(arr);
//            System.out.println("After Heapify:\n" + this);
//            System.out.println("After Heapify: this.size\n" + this.size);
            for (int i=this.size; i>=1; i--){
                Node temp = this.tree.get(1);
                this.tree.set(1, this.tree.get(i));
                this.tree.set(i, temp);
                this.size--;
                this.sift_down(1);
            }
            return this.tree.subList(1, arr.length+1);
        }


        public String toString(){
            return this.tree.subList(1, this.size+1).toString();
        }
    }
}