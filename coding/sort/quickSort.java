import java.util.Arrays;
import java.util.Collections;


class Scratch {
    public static void main(String[] args) {
        int[] arr = new int[]{2,5,3,7,3,4};
        int[] arr2 = new int[]{2,5,3,7,3};

        Quicksort.sort(arr);
        Quicksort.sort(arr2);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));

    }

    public static class Quicksort{

        private static void exchange(int idx1, int idx2, int[] arr){
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }

        private static int partition(int lo, int high, int[] arr){
            int i = lo+1;
            int j = high;
            while (i<j){
                //Don't put if in the loop, otherwise slow down by factor of 5.
                while(arr[i] < arr[lo] && i < high){
                    i += 1;
                }
                while(arr[j] > arr[lo]){
                    j -= 1;
                }
                if (i < j) { //in case i and j already crossed each other.
                    exchange(i, j, arr);
                }
            }
            exchange(lo, j, arr);
            return j;
        }

        private static void sort(int lo, int high, int[] arr){

            if (high <= lo) return;
            int j = partition(lo, high, arr);
            sort(lo, j-1, arr);
            sort(j+1, high, arr);
        }

        public static void sort(int[] arr){
//            Collections.shuffle(Arrays.asList(arr));
//            System.out.println(arr);
            sort(0, arr.length-1, arr);
        }


    }
}