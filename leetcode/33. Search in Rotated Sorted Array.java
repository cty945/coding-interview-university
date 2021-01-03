class Scratch {
    public static void main(String[] args) {

        int result = search(new int[]{4,5,6,7,0,1,2}, 6);
        System.out.println("result: " + result);
    }


    // find the value == target
    public static int search(int[] nums, int target) {

        int N = nums.length;

        int smallest_idx = binary_search_smallest(nums, 0, nums.length-1);

//        System.out.println("smallest_idx: " + smallest_idx);
        if (target < nums[smallest_idx] ||
                (smallest_idx == 0 && nums[N-1] < target || smallest_idx > 0 && nums[smallest_idx-1] < target)){
            return -1;
        }
        if (nums[N-1] < target){
            return binary_search(nums, 0, smallest_idx-1, target);
        } else {
            return binary_search(nums, smallest_idx, N-1, target);
        }

    }


    public static int binary_search_smallest(int[] nums, int low, int high){
        //base case
        if (low == high){
            return low;
        }
        int N = nums.length;
        int mid = (low + high)/2;
        if (nums[mid] < nums[N-1]){
            return binary_search_smallest(nums, low, mid);
        } else {
            return binary_search_smallest(nums, mid+1, N-1);
        }
    }


    public static int binary_search(int[] nums, int low, int high, int target){

        if (low >= high){
            return nums[low] == target ? low : -1;
        }

        int mid = (low+high)/2;
        if (nums[mid] > target){
            return binary_search(nums, low, mid-1, target);
        } else if (nums[mid] < target){
            return binary_search(nums, mid+1, high, target);
        } else {
            return mid;
        }
    }

}