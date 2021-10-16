import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Scratch {
    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(255));

        countBit(131);
//        System.out.println(res);
    }

    public static void countBit(int num) {

        int res1 = countBitBacktrack(num);
        int res2 =  countBitDP1(num);
        int res3 =  countBitDP2(num);

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);

    }

    /**
     * Position i relies on position i & (i-1). Add back 1 because i & (i-1) removes one set bit from i.
     * @param num
     * @return
     */
    private static int countBitDP2(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;

        for (int i = 1; i <= num; i++) {
            dp[i] = dp[i & (i-1)] + 1;
        }
        return dp[num];
    }

    /**
     * Position i relies on position dp[i / 2]. Basically is right shift 1 and add back the 1 that's been shifted
     * away if there existed 1.
     * @param num
     * @return
     */
    private static int countBitDP1(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;

        for (int i = 1; i <= num; i++) {
            int removed_1 = i & 1;
            dp[i] = dp[i / 2] + removed_1;
        }
        return dp[num];
    }

    private static int countBitBacktrack(int num) {
        Map<Integer, Integer> result = new HashMap<>();
        helper(0, 0, 0, result);
        if (result.get(num) != null) {
            return result.get(num);
        } else {
            return -1;
        }
    }

    private static void helper(int idx, int cur, int count, Map<Integer, Integer> result) {

        if (idx == 7) {
//            System.out.println("cur:" + cur);
//            System.out.println("count:" + count);

            result.put(cur, count);
            result.put(cur + 1, count + 1);

        } else {
            helper(idx + 1, (cur | 1) << 1, count + 1, result);
            helper(idx + 1, cur << 1, count, result);
        }
    }
}