class Scratch {
    public static void main(String[] args) {
    }

    //This is find the index of the 1st value that is >= target
    public int searchInsert(int[] nums, int target) {

        int low = 0;
        int high = nums.length;

        while (low < high){
            int mid = (low + high)/2;

            if (nums[mid] < target){
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }


}