import java.util.*;

public class StringArray {

    public static void main(String[] args){
        System.out.println(longestValidParentheses("()(())((((()"));

        System.out.println(minRemoveToMakeValid("))(("));

        int[][] intervals = {{1,3}, {2,6},{8,10},{15,18}};
        System.out.println(merge(intervals));
    }
//    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
    public boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for(int i=0; i<s.length(); i++){
            char c=s.charAt(i);
            if(c=='('){
                stack.push(c);
            }else if(c==')'){
                if(stack.isEmpty() || stack.pop()!='(') return false;
            }else if(c=='{'){
                stack.push(c);
            }else if(c=='}'){
                if(stack.isEmpty() || stack.pop()!='{') return false;
            }else if(c=='['){
                stack.push(c);
            }else if(c==']'){
                if(stack.isEmpty() || stack.pop()!='[') return false;
            }
        }
        return stack.isEmpty();
    }

    public Integer[] findBuildings(int[] heights) {
        int max=0;
        LinkedList<Integer> list=new LinkedList<>();
        for(int i=heights.length-1;i>=0;i--){
            if(heights[i]>max){
                max=heights[i];
                list.addFirst(i);
            }
        }
        list.toArray(new Integer[list.size()]);
        Integer[] ans=(Integer[])list.toArray();
        return list.toArray(new Integer[list.size()]);
    }
    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        int sum=0;
        int count=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(sum==k) count++;
            count+=map.getOrDefault(sum-k,0);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return count;
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a, b)->Integer.compare(a[0],b[0]));
        List<int[]> result=new ArrayList<>();
        result.add(intervals[0]);
        for(int i=1;i<intervals.length;i++){
            int[] current=result.get(result.size()-1);
            if(current[1]>=intervals[i][0]){
                current[1]=Math.max(intervals[i][1],current[1]);
            }else{
                result.add(intervals[i]);
            }
        }
        return result.toArray(new int[result.size()][2]);
    }

    public static String minRemoveToMakeValid(String s) {
        StringBuilder result = removeInvalidClosing(s, '(', ')');
        result = removeInvalidClosing(result.reverse(), ')', '(');
        return result.reverse().toString();
    }
    private static StringBuilder removeInvalidClosing(CharSequence string, char open, char close) {
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == open) {
                balance++;
            } if (c == close) {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }
        return sb;
    }

    public static int longestValidParentheses(String s) {
        int max=0;
        int current=0;
        int left=0;
        int right=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                left+=1;
            }else{
                right+=1;
            }
            if(right>left){
                left=0; right=0;
                continue;
            }
            if(left==right && left>0){
                current=left*2;
                if(current>max) max=current;
            }
        }

        current=0;
        left=0;
        right=0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)==')'){
                right+=1;
            }else{
                left+=1;
            }
            if(right<left){
                left=0; right=0;
                continue;
            }
            if(left==right && right>0){
                current=left*2;
                if(current>max) max=current;
            }
        }

        return max;
    }
}
