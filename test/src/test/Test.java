package test;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args){
        int[] n={5,2,8,3,1,9};
//        mergeSort(n,0,n.length-1);
//        quickSort(n,0,n.length-1);
//
//        for (int i = 0; i < n.length; i++) {
//            System.out.print(n[i]+", ");
//        }

//        ListNode list=new ListNode(1);
//        list.next=new ListNode(2);
//        list.next.next=new ListNode(3);
//        list.next.next.next=new ListNode(4);
//        list.next.next.next.next=new ListNode(5);
//        Solution s=new Solution();
//        System.out.println(s.reverseKGroup(list,2));

//        printAllQueen();

//        System.out.println(getMaxFreqDeviation("bbacccabab"));
//        getMaxFreqDeviation("bbacccabab");


//        System.out.println(minMovesToMakePalindrome("skwhhaaunskegmdtutlgtteunmuuludii"));
//        distinctSubString("aabb","");
//        getMaxDeviation("abcde");
//        System.out.println(getLongestCommonSubstring("abacdfgdcaba","abacdgfdcaba"));

//        System.out.println(isMatch("aa","ab"));

//        System.out.println(longestCommonSubString("navidpashmam","pashkiri"));
//        System.out.println(uniqueLetterString("abc"));

//        System.out.println(Integer.toBinaryString(setBit(0,1,4)));
//        System.out.println(Integer.toBinaryString(493438));
//        System.out.println(Integer.toBinaryString(1<<1));
//        System.out.println((493438 & 1<<1)==1);

//        char[][] grid={ {'1','1','1','1','0'},
//                        {'1','1','0','1','0'},
//                        {'1','1','0','0','0'},
//                        {'0','0','0','0','0'}};
//
//        numIslands(grid);

        int[][] cuboids={{1,2,3},{4,5,6}};
        maxHeight(cuboids);

    }
    public static int maxHeight(int[][] cuboids) {
        for(int i=0;i<cuboids.length;i++){
            int max=0;
            int index=0;
            for(int j=0;j<3;j++){
                if(cuboids[i][j]>max){
                    max=cuboids[i][j];
                    index=j;
                }
            }
            int temp=cuboids[i][0];
            cuboids[i][0]=max;
            cuboids[i][index]=temp;
        }
        Arrays.sort(cuboids,(a,b)->
        {
            if(a[0]>b[0]) return -1;
            if(a[0]<b[0]) return 1;
            return 0;
        });
        return 0;
    }

    public static int numIslands(char[][] grid) {
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        setArrayToFalse(visited);
        Queue<MyPoint> queue=new LinkedList<MyPoint>();
        int count=0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(visited[i][j]==true) continue;
                visited[i][j]=true;
                if(grid[i][j]=='1'){
                    queue.add(new MyPoint(i,j));
                    count++;
                    visitAllIsland(queue,visited,grid);
                }
            }
        }
        return count;
    }

    public static void visitAllIsland(Queue<MyPoint> queue, boolean[][] visited, char[][] grid){
        while(!queue.isEmpty()){
            MyPoint p=queue.poll();
            if((p.j+1<visited[p.i].length) && visited[p.i][p.j+1]==false){
                visited[p.i][p.j+1]=true;
                if(grid[p.i][p.j+1]=='1'){
                    queue.add(new MyPoint(p.i,p.j+1));
                }
            }
            if((p.i+1< visited.length) && visited[p.i+1][p.j]==false){
                visited[p.i+1][p.j]=true;
                if(grid[p.i+1][p.j]=='1'){
                    queue.add(new MyPoint(p.i,p.j+1));
                }
            }
        }
    }

    public static void setArrayToFalse(boolean[][] visited){
        for(int i=0; i<visited.length; i++){
            for(int j=0; j<visited[i].length; j++){
                visited[i][j]=false;
            }
        }
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        List<int[]> result=new ArrayList<>();
        result.add(intervals[0]);
        for(int i=1;i<intervals.length;i++){
            int[] current=result.get(result.size()-1);
            if(current[1]>=intervals[i][0]){
                result.remove(result.size()-1);
                current[1]=intervals[i][1];
                result.add(current);
            }else{
                result.add(intervals[i]);
            }
        }
        int[][] ans=new int[result.size()][2];
        for(int i=0;i< result.size();i++){
            ans[i]=result.get(i);
        }
        return result.toArray(new int[result.size()][2]);
    }

    public static int setBit(int num, int i, int j){
        int a=(1<<i)-1;
        int b=(1<<j)-1;
        int mask=a^b;
        return mask | num;
    }

    public static int uniqueLetterString(String s) {
        HashMap<Character, List<Integer>> charPositions = new HashMap<>();
        int n = s.length();

        for(int i=0;i<n;i++) {
            char ch = s.charAt(i);
            charPositions.putIfAbsent(ch, new ArrayList<>());
            charPositions.get(ch).add(i);
        }
        // System.out.println(charPositions);

        int result = 0;
        for(List<Integer> index: charPositions.values()) {
            for(int i=0;i<index.size();i++) {
                int left = i==0 ? index.get(i) + 1 : index.get(i) - index.get(i-1);
                int right = i==index.size()-1 ? n - index.get(i): index.get(i+1) - index.get(i);
                result += left * right;
            }
        }

        return result;
    }

    public static String longestCommonSubString(String s1,String s2){

        int[][] mem=new int[s1.length()][s2.length()];
        int max=0;
        int index=0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if(s1.charAt(i)==s2.charAt(j)){
                    if(i==0 || j==0){
                        mem[i][j]=1;
                    }else {
                        mem[i][j]=mem[i-1][j-1]+1;
                    }
                    if(mem[i][j]>max){
                        max=mem[i][j];
                        index=i;
                    }
                }else{
                    mem[i][j]=0;
                }
            }
        }
        return s1.substring(index-max+1,index+1);
    }

    public static boolean isMatch(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
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

    static int getMaxDeviation(String s){
        Map<Character,Integer> map=new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        Set<Character> set=map.keySet();
//        Object[] chars=set.stream().toArray();
        int max=0;
        for (char c1:set) {
            for (char c2:set) {
                if(c1==c2) continue;
                int c1Count=0;
                int c2Count=0;
                int c2Remaining=map.get(c2);
                for (int k = 0; k < s.length(); k++) {
                    if(s.charAt(k)==c1) c1Count++;
                    if(s.charAt(k)==c2) {
                        c2Count++;
                        c2Remaining--;
                    }
                    if(c2Count>c1Count && c2Remaining>0){
                        c1Count=0;
                        c2Count=0;
                    }
                    if(c2Count==0) max=Math.max(max,c1Count-1);
                    max=Math.max(max,c1Count-c2Count);
                }
            }
        }
        return max;
    }
    static void distinctSubString(String re,String prefix){
        if(re.length()==0) System.out.println(prefix);
        Set<Character> set=new HashSet<>();
        for (int i = 0; i <re.length() ; i++) {
            if(!set.contains(re.charAt(i))){
                set.add(re.charAt(i));
                String current=re.substring(0,i)+re.substring(i+1);
                distinctSubString(current,prefix+re.charAt(i));
            }
        }
    }

    public static int minMovesToMakePalindrome(String str) {
        int count = 0;
        String s=str;
        int i=0, j=s.length()-1;
        boolean flag=false;
        while(j>0){
            String current=s;
            if(s.charAt(i)!=s.charAt(j)){
                int index=s.lastIndexOf(s.charAt(i));
                if(index==i) {
                    if(flag || s.length()%2==0){
                        return -1;
                    }
                    count+=s.length()/2;
                    s=s.substring(1);
                    flag=true;
                    j=s.length()-1;
                    continue;
                }
                count+=j-index;
                current=swap(s,index,j);
                ;            }
            s=current.substring(i+1,j);
            j=s.length()-1;
        }
        return count;
    }

    private static String swap(String s,int index, int j) {
        char temp=s.charAt(index);
        char[] chars=s.toCharArray();
        int count=0;
        while(index!=j){
            count++;
            chars[index]=chars[index+1];
            index++;
        }
        chars[index]=temp;

        return String.valueOf(chars);
    }



    public int largestVariance(String s) {
        int n = s.length();;
        int[][] arr = new int[n+1][26];
        for(int i=1; i<=n; i++) {
            for(int j=0; j<26; j++) {
                arr[i][j] = arr[i-1][j];
            }
            arr[i][s.charAt(i-1)-'a']++;
        }
        int maxDev = 0;
        for(int i=1; i<=n; i++) {
            for(int j=i; j<=n; j++) {
                int min = n, max = 1;
                for(int k=0; k<26; k++) {
                    int cnt = arr[j][k] - arr[i-1][k];
                    if(cnt==0) continue;
                    min = Math.min(min,cnt);
                    max = Math.max(max,cnt);
                }
                maxDev = Math.max(maxDev, max-min);
            }
        }
        return maxDev;
    }

    public static int getDev(Map<String, Integer> devEntries){
        List<Integer> entries = devEntries.entrySet().stream()
                .map(x->x.getValue())
                .collect(Collectors.toList());
        Comparator<Integer> collect = Comparator.naturalOrder();
        Collections.sort(entries,collect.reversed());
        return entries.get(0) - entries.get( entries.size()-1);
    }
    public static int getMaxFreqDeviation(String s ) {
        Set<Integer> deviations=new HashSet<>();
        for (int x=0;x<s.length();x++) {
            for (int g=x;g<s.length()+1;g++){
                String su =s.substring(x,g);
                Map<String, Integer> map = Arrays.asList(su.split(""))
                        .stream()
                        .collect(Collectors.groupingBy(v->v,Collectors.summingInt(v->1)));
                if (map.entrySet().size()==1){
                    deviations.add(Math.abs(0));
                }else {
                    int devcount = getDev(map);
                    deviations.add(Math.abs(devcount));
                }
            }

        }
        return deviations.stream().collect(Collectors.toList()).get(deviations.size()-1);
    }

    public static int getMaxFreqDeviation2(String s) {
        // Write your code here
        int count=1;
        int maxDev=0;
        char current=s.charAt(0);
        for (int i = 1; i < s.length()-1; i++) {
            if(s.charAt(i)==current){
                count++;
            }else{
                if(count>maxDev){
                    maxDev=count;
                }
                count=1;
                current=s.charAt(i);
            }
        }
        return maxDev-1;
    }

    public static int getMaxDeviationForSubString(String s){
        if(s.isEmpty()) return 0;
        char[] chars=s.toCharArray();
        Map<Character, Integer> map=new HashMap<>();
        for (Character c:chars) {
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,1);
            }
        }

        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for (Character c:map.keySet()) {
            int value=map.get(c);
            if(value>max){
                max=value;
            }
            if(value<min){
                min=value;
            }
        }
        return max-min;
    }




    private static int countOnly(String s)
    {
        int n = s.length();
        int count = 0;
        char[] inputAsCharArray = s.toCharArray();
        for (int i = 0; i < n / 2; i++)
        {
            if (inputAsCharArray[i] != inputAsCharArray[n - i - 1])
            {
                count++;
            }
        }
        if (count % 2 == 1 && n % 2 == 0)
            return -1;
        return (count + 1) / 2;
    }





    private static boolean valid(int row2, int column2, int[] columns) {
        for (int row = 0; row < row2; row++) {
            if(column2==columns[row]) return false;
            int rowDifference=row2-row;
            int columnDifference=Math.abs(column2-columns[row]);
            if(rowDifference==columnDifference) return false;
        }
        return true;
    }

    public static void mergeSort(int[] input, int p, int r){
        if(p<r){
            int q=(p+r)/2;
            mergeSort(input,p,q);
            mergeSort(input,q+1,r);
            sort(input,p,r);
        }
    }
    public static void sort(int[] n,int p,int r){
        int[] current=new int[r-p+1];
        for (int i = 0; i < current.length; i++) {
            current[i]=n[p+i];
        }
        int right=((current.length-1)/2)+1;
        int left=0;

        for (int i = p; i <=r ; i++) {
            if(left>=((current.length-1)/2)+1 || (right<current.length && current[left]>current[right])){
                n[i]=current[right];
                right++;
            }else{
                n[i]=current[left];
                left++;
            }
        }
    }

    public static void quickSort(int[] n,int p,int r){
        if(p<r) {
            int index = partition(n, p, r);
            quickSort(n, p, index - 1);
            quickSort(n, index + 1, r);
        }
    }

    private static int partition(int[] n, int p, int r) {
        int pivotal=n[r];
        int q=p-1;

        for (int i = p; i <=r ; i++) {
            if(n[i]<=pivotal){
                q++;
                int temp=n[q];
                n[q]=n[i];
                n[i]=temp;
            }
        }
        return q;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        int lenght=getLenght(head);
        int loop=lenght/k;
        ListNode current=head;
        ListNode nHead=null;
        ListNode nCurrent=null;

        for(int i=0; i<loop; i++){

            Stack<ListNode> stack=new Stack<>();
            for(int j=0; j<k; j++){
                if(current!=null){
                    stack.push(current);
                    current=current.next;
                }
            }
            if(nHead==null && !stack.isEmpty()) {
                nHead=stack.peek();
                nCurrent=nHead;
            }

            while(!stack.isEmpty()){
                nCurrent.next=stack.pop();
                nCurrent=nCurrent.next;
            }
            nCurrent.next=current;
        }
        return nHead;
    }
    private int getLenght(ListNode head){
        ListNode current=head;
        int result=0;
        while(current!=null){
            result++;
            current=current.next;
        }
        return result;
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        Column current=new Column(0,root);
        LinkedList<Column> list=new LinkedList<>();
        list.add(current);
        while(!list.isEmpty()){
            current=list.poll();
            if(current.node.left!=null){
                list.add(new Column(current.column-1,current.node.left));
            }
            if(current.node.right!=null){
                list.add(new Column(current.column+1,current.node.right));
            }
        }
        Collections.sort(list);
        List<List<Integer>> lists=new ArrayList<>();
        List<Integer> cur=new ArrayList<>();
        cur.add(list.get(0).node.val);
        lists.add(cur);
        for (int i = 1; i < list.size(); i++) {
            current=list.get(i);
            if(current.column==list.get(i-1).column){
                cur.add(current.node.val);
            }else{
                cur=new ArrayList<>();
                cur.add(current.node.val);
                lists.add(cur);
            }
        }
        return lists;
    }
}
class MyPoint {
    int i;
    int j;
    public MyPoint(int i, int j){
        this.i=i;
        this.j=j;
    }
}
class LRUCache {
    DoubleLinkedList head;
    DoubleLinkedList tail;

    class DoubleLinkedList{
        int key;
        int value;
        DoubleLinkedList next;
        DoubleLinkedList prev;
    }

    public void add(DoubleLinkedList node){
        node.next=head.next;
        node.prev=head;
        head.next.prev=node;
        head.next=node;
    }

    public void remove(DoubleLinkedList node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public void moveToHead(DoubleLinkedList node){
        remove(node);
        add(node);
    }

    public int popTail(){
        int key=tail.prev.key;
        tail.prev.prev.next=tail;
        tail.prev=tail.prev.prev;
        return key;
    }

    Map<Integer,DoubleLinkedList> map;
    int capacity;
    int size=0;
    public LRUCache(int capacity) {
        head=new DoubleLinkedList();
        tail=new DoubleLinkedList();
        head.next=tail;
        tail.prev=head;
        map=new HashMap<>();
        this.capacity=capacity;
    }

    public int get(int key) {
        DoubleLinkedList node=map.getOrDefault(key,null);
        if(node==null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            DoubleLinkedList node=map.get(key);
            node.value=value;
            moveToHead(node);
        }else{
            DoubleLinkedList node=new DoubleLinkedList();
            node.key=key;
            node.value=value;
            add(node);
            size++;
            map.put(key,node);
            if(size>capacity){
                int index=popTail();
                map.remove(index);
                size--;
            }
        }
    }

}
class Column implements Comparable<Column>{
    int column;
    TreeNode node;

    public Column(int column,TreeNode node){
        this.column=column;
        this.node=node;
    }

    @Override
    public int compareTo(Column o) {
        return Integer.compare(column,o.column);
    }
}
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
      TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
       this.val = val;
        this.left = left;
        this.right = right;
      }
 }