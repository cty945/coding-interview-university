import java.util.Arrays;

class Scratch {

    public static void main(String[] args) {

//        int[] arr = new int[]{5,3,2,0,1,6,5};
        int[] arr = new int[]{3,5,2,8,1,6,5,9};
//        int[] arr = new int[]{3,5,2,8,1,6,5,9};

        Mergesort ms = new Mergesort();
        ms.mergeSort(arr);
//        ms.mergeSortButtonUp(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static class Mergesort{

        private void merge(int low, int mid, int high, int[] arr, int[] temp){

            int i = low;
            int j = mid + 1;

            for (int k=low; k <= high; k++){
                if (i > mid) arr[k] = temp[j++];
                else if (j > high) arr[k] = temp[i++];
                else if (temp[i] > temp[j]) arr[k] = temp[j++];
                else arr[k] = temp[i++];
            }
        }

        private void sort(int low, int high, int[] arr, int[] temp){
            if (low >= high) return;

            int mid = (low + high)/2;
            sort(low, mid, temp, arr);
            sort(mid+1, high, temp, arr);
            merge(low, mid, high, arr, temp);
        }


        public void mergeSort(int[] arr){

            int[] temp = arr.clone();
            this.sort(0, arr.length-1, arr, temp);

        }

        public void mergeSortButtonUp(int[] arr){

            int N = arr.length;

            for (int step=1; step < N; step+=step){
                for (int i=0; i+step < N; i+=2*step){
                    int[] temp = arr.clone();
                    this.merge(i, i+step-1, Math.min(i+2*step-1, N-1) , arr, temp);
                }
            }

        }
    }

}