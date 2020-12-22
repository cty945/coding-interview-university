package leetcode;

import java.util.HashMap;
import java.util.Map;

class Main {

    public static void main(String[] args) {

        System.out.println("123");

        Solution s = new Solution();
        s.twoSum(new int[]{1, 2, 3}, 1);
    }
}


class Solution {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i=0; i<nums.length; i++){
            int wantKey = target - nums[i];
            if (map.containsKey(wantKey)){
                return new int[]{map.get(wantKey), i};
            }
            map.put(nums[i], i);
        }

        return new int[0];
    }
}
