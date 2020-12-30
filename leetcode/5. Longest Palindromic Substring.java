class Scratch {
    public static void main(String[] args) {
        
    }

    public String longestPalindrome(String s) {

        if (s.length() == 0){
            return "";
        }

        int N = s.length();
        String result = s.substring(0, 1);

        boolean dp[][] = new boolean[N][N];

        for (int i=0; i<s.length();i++){
            dp[i][i] = true;
        }
        for (int i=0; i<s.length()-1; i++){
            dp[i][i+1] = s.charAt(i) == s.charAt(i+1);
            if (dp[i][i+1]){
                result = s.substring(i, i+2);
            }
        }

        //So, if there are no result in 2 consecutive rounds, we know that there won't be any longer palindrome
        //and we can early exit.
        int notFoundCounter = 0;
        for (int step=2; step<N; step++){
            if (notFoundCounter == 2){
                return result;
            }
            boolean found = false;
            for (int i=0; i<N-step; i++){
                dp[i][i+step] = dp[i+1][i+step-1] && s.charAt(i) == s.charAt(i+step);
                if (dp[i][i+step]){
                    found = true;
                    notFoundCounter = 0;
                    result = s.substring(i, i+step+1);
                }
            }
            if (!found){
                notFoundCounter += 1;
            }
        }

        return result;
    }


}