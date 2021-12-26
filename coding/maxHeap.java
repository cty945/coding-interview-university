import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Scratch {
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        heap.insert(1);
        heap.insert(3);
        heap.insert(2);
        heap.insert(5);

        heap.print_values();
        int max = heap.extract_max();
        System.out.println("max: " + max);
        heap.print_values();

        System.out.println("remove idx 1: ");
        heap.remove(2);
        heap.print_values();

        int[] temp = new int[]{1, 3, 5, 7, 4, 2};
        heap.heap_sort(temp);
        System.out.println(Arrays.toString(temp));

    }
}


class MaxHeap {

    int size = 0;
    List<Integer> arr;

    MaxHeap() {
        this.arr = new ArrayList<>(10);
    }

    public void insert(int value) {
        this.arr.add(this.size, value);
        this.size++;
        this.shift_up(this.size - 1);
    }

    private void swap(int i, int j) {
        int temp = this.arr.get(i);
        this.arr.set(i, this.arr.get(j));
        this.arr.set(j, temp);
    }

    private void shift_up(int idx) {
        while (idx > 0 && this.arr.get((idx - 1) / 2) < this.arr.get(idx)) {
            this.swap((idx - 1) / 2, idx);
            idx = (idx - 1) / 2;
        }
    }

    private void shift_down(int idx, int n) {
        int l = 2 * idx + 1;
        int r = 2 * idx + 2;
        int largestIdx = idx;

        if (l < n && this.arr.get(l) > this.arr.get(largestIdx)) {
            largestIdx = l;
        }

        if (r < n && this.arr.get(r) > this.arr.get(largestIdx)) {
            largestIdx = r;
        }

        if (largestIdx != idx) {
            this.swap(idx, largestIdx);
            this.shift_down(largestIdx, n);
        }
    }

    public void print_values() {
        System.out.println(this.arr.subList(0, this.size));
    }

    public int get_max() {
        return this.arr.size() > 0 ? this.arr.get(0) : -1;
    }

    public int get_size() {
        return this.size;
    }

    public boolean is_empty() {
        return this.size == 0;
    }

    public int extract_max() {
        int result = this.arr.get(0);
        this.swap(0, this.size - 1);
        this.size--;
        this.shift_down(0, this.size);
        return result;
    }

    public void remove(int i) {
        this.swap(i, this.size - 1);
        this.size--;
        this.shift_down(i, this.size);
    }

    private void heapify(int[] inputArr) {
        this.arr = new ArrayList<>(10);
        this.size = 0;

        for (int i : inputArr) {
            this.arr.add(i);
            this.size++;
        }

        for (int i = this.size / 2 - 1; i >= 0; i--) {
            this.shift_down(i, this.size);
        }
        System.out.println("heapify done:" + this.arr);
    }

    public void heap_sort(int[] inputArr){
        this.heapify(inputArr);

        int size = this.size;
        for (int i=0; i < size; i++){
            inputArr[inputArr.length-i-1] = this.arr.get(0);
            this.swap(0, this.size-1);
            this.size--;
            this.shift_down(0, this.size);
        }
        System.out.println("heap:");
        this.print_values();
    }


}