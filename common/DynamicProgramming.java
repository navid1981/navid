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

    //Swap nodes on pair
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode nHead=head.next;
        head.next=swapPairs(head.next.next);
        nHead.next=head;
        return nHead;
    }

    //reverse linkedlist
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode p=reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return p;
    }

    //There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
//Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
    int n;
    int m;
    int[][] mem;
    public int uniquePaths(int m, int n) {
        mem=new int[m][n];
        this.m=m;
        this.n=n;
        for(int i=0;i<mem.length;i++){
            Arrays.fill(mem[i],-1);
        }
        return dp(0,0);
    }

    private int dp(int m, int n){
        if(m>this.m-1 || n>this.n-1) return 0;
        if(m==this.m-1 && n==this.n-1) return 1;
        if(mem[m][n]!=-1) return mem[m][n];
        mem[m][n]=dp(m,n+1)+dp(m+1,n);
        return mem[m][n];
    }

//    You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
//    Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
    public int change(int amount, int[] coins) {
        mem1=new Integer[amount+1][coins.length];
        return dp(amount,0,coins);
    }
    Integer[][] mem1;
    private int dp(int re,int index, int[] coins){
        if(re<0 || index>=coins.length) return 0;
        if(re==0) return 1;
        if(mem1[re][index]!=null) return mem1[re][index];
        int count=dp(re,index+1,coins);
        if(re-coins[index]>=0){
            count+=dp(re-coins[index],index,coins);
        }
        mem1[re][index]=count;
        return count;
    }

    //N-Queen Back-track
    int count=0;
    Set<Integer> setCol=new HashSet<>();
    Set<Integer> setDia=new HashSet<>();
    Set<Integer> setUnDia=new HashSet<>();

    public int totalNQueens(int n) {
        totalNQueens(n,0);
        return count;
    }

    private void totalNQueens(int n, int row){
        if(row==n){
            count++;
        }
        for(int j=0;j<n;j++){
            if(!setCol.contains(j) && !setDia.contains(j-row) && !setUnDia.contains(j+row)){
                setCol.add(j);
                setDia.add(j-row);
                setUnDia.add(j+row);
                totalNQueens(n,row+1);
                setCol.remove(j);
                setDia.remove(j-row);
                setUnDia.remove(j+row);
            }
        }
    }

//    Given an integer array nums of unique elements, return all possible
//    subsets.
//    The solution set must not contain duplicate subsets. Return the solution in any order.
//    Input: nums = [1,2,3]
//    Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    List<List<Integer>> list1=new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums){
        list1.add(new ArrayList<>());
        re(nums,0,new LinkedList<>());
        return list1;
    }
    private void re(int[] nums, int index, LinkedList<Integer> list){
        if(index==nums.length){
            return;
        }
        for(int i=index; i<nums.length;i++){
            list.addLast(nums[i]);
            list1.add(new ArrayList<>(list));
            re(nums,i+1,list);
            list.removeLast();
        }
    }
}

 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }