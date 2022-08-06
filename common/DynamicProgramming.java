import java.util.*;

public class DynamicProgramming {

    public static void main(String[] args){
//        System.out.println(getLongestCommonSubstring("abacdfgdcaba","abacdgfdcaba"));

//        System.out.println(longestPalindrome("cbbd"));

//        int[] arr={-1,2,4,-6,7};
//        System.out.println(maxSubArray(arr));

    }

//    Kadane's Algorithm
    public static int maxSubArray(int[] nums) {
        int len=nums.length;
        int max=nums[0];
        int currentV=nums[0];
        for(int i=1;i<len;i++){
            currentV=Math.max(nums[i], currentV+nums[i]);
            if(currentV>max) max=currentV;
        }
        return max;
    }

    public static String longestPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        int len = s.length();
        String ans = "";
        boolean[][] dp = new boolean[len][len];

        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                boolean judge = s.charAt(i) == s.charAt(j);
                dp[i][j] = j - i > 2 ? dp[i + 1][j - 1] && judge : judge;
                if (dp[i][j] && s.substring(i,j+1).length()>ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    public static String getLongestCommonSubstring(String a, String b){
        int m = a.length();
        int n = b.length();
        int max = 0;
        int f=0;
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(a.charAt(i) == b.charAt(j)){
                    if(i==0 || j==0){
                        dp[i][j]=1;
                    }else{
                        dp[i][j] = dp[i-1][j-1]+1;
                    }

                    if(max < dp[i][j]) {
                        max = dp[i][j];
                        f = i;
                    }
                }
            }
        }
        return a.substring(f-max+1,f+1);
    }
}
