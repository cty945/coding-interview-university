class Scratch {
    public static void main(String[] args) {

    }


    public int search(int[] nums, int target) {

        if (nums.length == 0) {
            return -1;
        }

        int N = nums.length;

        if (nums[N - 1] >= nums[0]) {
            return binary_search(nums, 0, N - 1, target);
        }

        //It must have been rotated, find the pivot.
        int smallest_idx = find_smallest_idx(nums, 0, N - 1);
        if (nums[smallest_idx] > target || nums[smallest_idx - 1] < target) {
            return -1;
        }
        if (nums[N - 1] >= target) {
            return binary_search(nums, smallest_idx, N - 1, target);
        } else {
            return binary_search(nums, 0, smallest_idx - 1, target);
        }
    }

    /**
     * Find the first x that's smaller or equal than nums[N-1]
     *
     * @param nums
     * @param lo
     * @param hi
     * @return
     */
    private int find_smallest_idx(int[] nums, int lo, int hi) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= nums[0]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private int binary_search(int[] nums, int lo, int hi, int target) {
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if (nums[mid] == target){
                return mid;
            } else if (nums[mid] < target){
                lo = mid + 1;
            } else if (nums[mid] > target){
                hi = mid;
            }
        }
        return nums[lo] == target ? lo : -1;
    }
}

