import java.util.*;

public class RecursionDynamic {
    public static void main(String[] args){
        //8.1
//        System.out.println(getNumStep2(5,new int[6]));

        //8.2
//        boolean[][] grid={
//                {true,true,true,true,true},
//                {true,true,true,false,true},
//                {false,false,false,true,true},
//                {false,false,false,true,true}
//        };
//        List<Point> list=getPath(grid);
//        if(list!=null){
//            for (int i = list.size()-1; i > -1; i--) {
//                System.out.print(list.get(i).row+":"+list.get(i).column+", ");
//            }
//        }else{
//            System.out.println("No path was found");
//        }

        //8.3
//        int[] array={-3,-2,1,3,8,9};
//        System.out.println(magicIndex(array));

        //8.4
//        List<Integer> list=new ArrayList<>();
//        list.add(1); list.add(2); list.add(3);
//        List<List<Integer>> lists=getPowerSet(list);
//        for (int i = 0; i < lists.size(); i++) {
//            System.out.println();
//            for (int j = 0; j < lists.get(i).size(); j++) {
//                System.out.print(lists.get(i).get(j)+", ");
//            }
//        }

        //8.5
//        System.out.println(multiply(4,6));

        //8.6
//        Tower s=new Tower(0);
//        s.add(5); s.add(4); s.add(3); s.add(2); s.add(1);
//        Tower d=new Tower(1);
//        Tower b=new Tower(2);
//        moveTower(s.stack.size(),s,d,b);
//        System.out.println();

        //8.7
//        getAllpermutation("aa");

        //8.8
//        getAllUniquePermutation2("aaabbbbccccc");

        //8.9
//        printAllParentheses(3);

        //8.10
//        Color b=new Color("blue");
//        Color r=new Color("red");
//        Color w=new Color("white");
//        Color[][] matrix={
//                {b,b,b,b},
//                {b,b,b,b},
//                {b,b,b,b},
//                {w,w,r,b},
//        };
//        paintFill(matrix,new MyPoint(1,2),matrix[0][1].name,"yellow");
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                System.out.print(matrix[i][j].name+", ");
//            }
//            System.out.println();
//        }

        //8.11
//        Coin coin=new Coin(0,0,0,0);
//        List<Coin> list=new ArrayList<>();
//        Set<Coin> set =new HashSet<>();
//        getCoins(345,coin,list,set);
//        System.out.println(list.size());

        //8.11
//        int way=getTotalReminder(345);
//        System.out.println(way);

        //8.12
//        getEightQueens();

        //8.13
//        List<Box> list=new ArrayList<>();
//        Box box1=new Box(5,5,5);
//        Box box2=new Box(6,6,6);
//        Box box3=new Box(8,8,4);
//        Box box4=new Box(7,7,10);
//        Box box5=new Box(4,9,8);
//        list.add(box1); list.add(box2);
//        list.add(box3);
//        list.add(box4);
//        list.add(box5);
//        System.out.println(getMaxHeight(list));

        //8.14
        System.out.println(countEvaluation("0&0&0&1^1|0",true));

    }

    //8.14
    static int countEvaluation(String s,boolean result){
        Map<String,Integer> map=new HashMap<>();
        return countEvaluation(s,result,map);
    }

    private static int countEvaluation(String s, boolean result, Map<String, Integer> map) {
        if(s.isEmpty()) return 0;
        if(s.length()==1){
            return (s.equals("1")&result) || (s.equals("0")&!result)?1:0;
        }
        if(map.containsKey(result+s)){
            return map.get(result+s);
        }
        int totalWay=0;
        for (int i = 1; i < s.length(); i+=2) {
            char c=s.charAt(i);
            String left=s.substring(0,i);
            String right=s.substring(i+1);
            int leftTrue=countEvaluation(left,true,map);
            int leftFalse=countEvaluation(left,false,map);
            int rightTrue=countEvaluation(right,true,map);
            int rightFalse=countEvaluation(right,false,map);
            int total=(leftTrue+leftFalse)*(rightTrue+rightFalse);
            int wayTrue=0;
            if(c=='&'){
                wayTrue=leftTrue*rightTrue;
            }else if(c=='|'){
                wayTrue=(leftFalse*rightTrue)+(leftTrue*rightFalse)+(leftTrue*rightTrue);
            }else {
                wayTrue=(leftTrue*rightFalse)+(leftFalse*rightTrue);
            }
            int way=result?wayTrue:total-wayTrue;
            totalWay+=way;
        }
        map.put(result+s,totalWay);
        return totalWay;
    }


    //8.13
    static int getMaxHeight(List<Box> list){
        Collections.sort(list,new Box());
        int max=0;
        int[] map=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            int currentHeight=getMaxHeight(i,list,map);
            max=Math.max(max,currentHeight);
        }
        return max;
    }

    private static int getMaxHeight(int index, List<Box> list,int[] map) {
//        if(index>= list.size()) return 0;
        if(map[index]>0) {
            return map[index];
        }
        int max=list.get(index).height;
        for (int i = index+1; i < list.size(); i++) {
            if(validBox(list.get(index),list.get(i))) {
                int currentHeight = getMaxHeight(i , list,map) + list.get(index).height;
                max = Math.max(max, currentHeight);
            }
        }
        map[index]=max;
        return max;
    }

    private static boolean validBox(Box box, Box box1) {
        if(box.width<=box1.width) return false;
        if(box.depth<=box1.depth) return false;
        return true;
    }

    //8.12
    static void getEightQueens(){
        Integer[] colums=new Integer[8];
        List<Integer[]> list=new ArrayList<>();
        getEightQueens(0,colums,list);
        System.out.println(list.size());
    }

    private static void getEightQueens(int row, Integer[] colums, List<Integer[]> result) {
        if(row==8){
            result.add(colums.clone());
        }
        for (int i = 0; i < 8; i++) {
            if(valid(row,i,colums)){
                colums[row]=i;
                getEightQueens(row+1,colums,result);
            }
        }
    }

    private static boolean valid(int row, int colum, Integer[] colums) {
        for (int row2 = 0; row2 < row; row2++) {
            int colum2=colums[row2];
            if(colum==colum2) return false;
            int rowDifference=row-row2;
            int columDifference=Math.abs(colum-colum2);
            if(rowDifference==columDifference) return false;
        }
        return true;
    }


    //8.11
    static int getTotalReminder(int n){
        int[] denom= {25,10,5,1};
        int[][] map=new int[n+1][denom.length];
        return getTotalReminder(n,denom,0,map);
    }

    private static int getTotalReminder(int reminder, int[] denom, int index, int[][] map) {
        if(index>=denom.length-1){
            return 1;
        }
        if(map[reminder][index]>0){
            return map[reminder][index];
        }
        int way=0;
        for (int i = 0; denom[index]*i <= reminder ; i++) {
            way+=getTotalReminder(reminder-denom[index]*i,denom,index+1,map);
        }
        map[reminder][index]=way;
        return way;
    }

    //8.11
    static void getCoins(int reminder, Coin coin,List<Coin> coins, Set<Coin> set){
        if(reminder==0){
            coins.add(coin);
        }
//        Set<Coin> set=new HashSet<>();
        if(reminder>=25){
            if(!set.contains(new Coin(coin.quarters+1,coin.dimes,coin.nickels,coin.pennies))) {
                set.add(new Coin(coin.quarters + 1, coin.dimes, coin.nickels, coin.pennies));
                getCoins(reminder - 25, new Coin(coin.quarters + 1, coin.dimes, coin.nickels, coin.pennies), coins,set);
            }
        }
        if(reminder>=10){
            if(!set.contains(new Coin(coin.quarters,coin.dimes+1,coin.nickels,coin.pennies))){
                set.add(new Coin(coin.quarters,coin.dimes+1,coin.nickels,coin.pennies));
                getCoins(reminder-10,new Coin(coin.quarters,coin.dimes+1,coin.nickels,coin.pennies),coins,set);
            }

        }
        if(reminder>=5){
            if(!set.contains(new Coin(coin.quarters,coin.dimes,coin.nickels+1,coin.pennies))) {
                set.add(new Coin(coin.quarters,coin.dimes,coin.nickels+1,coin.pennies));
                getCoins(reminder - 5, new Coin(coin.quarters, coin.dimes, coin.nickels + 1, coin.pennies), coins,set);
            }
        }
        if(reminder>=1){
            if(!set.contains(new Coin(coin.quarters,coin.dimes,coin.nickels,coin.pennies+1))) {
                set.add(new Coin(coin.quarters,coin.dimes,coin.nickels,coin.pennies+1));
                getCoins(reminder - 1, new Coin(coin.quarters, coin.dimes, coin.nickels, coin.pennies + 1), coins,set);
            }
        }

    }

    //8.10
    static void paintFill(Color[][] matrix, MyPoint point, String color, String newColor){
        if(point.row>=matrix.length || point.row<0 || point.column>=matrix[point.row].length || point.column<0) return;
        if(matrix[point.row][point.column].name!=color){
            return;
        }
        matrix[point.row][point.column].name=newColor;

        MyPoint p1=new MyPoint(point.row+1,point.column);
        paintFill(matrix,p1,color,newColor);
        MyPoint p2=new MyPoint(point.row-1,point.column);
        paintFill(matrix,p2,color,newColor);
        MyPoint p3=new MyPoint(point.row,point.column+1);
        paintFill(matrix,p3,color,newColor);
        MyPoint p4=new MyPoint(point.row,point.column-1);
        paintFill(matrix,p4,color,newColor);

    }

    //8.9
    static List<String> printAllParentheses(int number){
        List<String> list=new ArrayList<>();
        printAllParentheses(number,number,"",list);
        return list;
    }

    private static void printAllParentheses(int left, int right, String s,List<String> list) {
        if(left<0 || right<left) {
            return;
        }
        if(left==0 && right==0){
            list.add(s);
        }else{
            String current=s+"(";
            printAllParentheses(left-1,right,current,list);
            current=s+")";
            printAllParentheses(left,right-1,current,list);
        }
    }

    //8.8
    static List<String> getAllUniquePermutation2(String s){
        Hashtable<Character,Integer> hashtable=getTable(s);
        List<String> list=new ArrayList<>();
        getAllUniquePermutation2(hashtable,list,"",s.length());
        return list;
    }
    private static void getAllUniquePermutation2(Hashtable<Character, Integer> hashtable, List<String> list, String prefix, int reminder) {
        if(reminder==0){
            list.add(prefix);
        }else{
            for (Character c:
                 hashtable.keySet()) {
                if(hashtable.get(c)>0){
                    hashtable.put(c,hashtable.get(c)-1);
                    getAllUniquePermutation2(hashtable,list,prefix+c,reminder-1);
                    hashtable.put(c,hashtable.get(c)+1);
                }
            }
        }
    }
    private static Hashtable<Character, Integer> getTable(String s) {
        Hashtable<Character,Integer> hashtable=new Hashtable<>();
        for (int i = 0; i < s.length(); i++) {
            if(hashtable.containsKey(s.charAt(i))){
                hashtable.put(s.charAt(i),hashtable.get(s.charAt(i))+1);
            }else{
                hashtable.put(s.charAt(i),1);
            }
        }
        return hashtable;
    }

    //8.8
    static List<String> getAllUniquePermutation(String s){
        List<String> list =new ArrayList<>();
        getAllUniquePermutation(s,"",list);
        return list;
    }

    private static void getAllUniquePermutation(String s, String prefix, List<String> list) {
        if(s.isEmpty()){
            list.add(prefix);
            System.out.println(prefix);
        }else{
            Set<Character> set=new HashSet<>();
            String current=null;
            String newPrefix=null;
            for (int i = 0; i < s.length(); i++) {
                if(set.contains(s.charAt(i))) {
                    continue;
                }
                set.add(s.charAt(i));

                current=s.substring(0,i)+s.substring(i+1);
                newPrefix=prefix+s.substring(i,i+1);
                getAllUniquePermutation(current,newPrefix,list);
            }
        }
    }

    //8.7
    static List<String> getAllpermutation(String s){
        List<String> list =new ArrayList<>();
        getAllpermutation(s,"",list);
        return list;
    }

    private static void getAllpermutation(String s, String prefix, List<String> list) {
        if(s.isEmpty()){
            list.add(prefix);
            System.out.println(prefix);
        }else{
            String current=null;
            String newPrefix=null;
            for (int i = 0; i < s.length(); i++) {
                current=s.substring(0,i)+s.substring(i+1);
                newPrefix=prefix+s.substring(i,i+1);
                getAllpermutation(current,newPrefix,list);
            }
        }
    }

    //8.6
    static void moveTower(int n, Tower source, Tower destination, Tower buffer){
        if(n<=0) return;
//        if(n==1) {
//            source.moveTo(destination);
//            return;
//        }

        moveTower(n-1,source,buffer,destination);
        source.moveTo(destination);
        moveTower(n-1,buffer,destination,source);
    }

    //8.5
    static int multiply(int a,int b){
        int smaller=a<=b?a:b;
        int bigger=a>b?a:b;
        return multiplyHelper(smaller,bigger);
    }

    private static int multiplyHelper(int smaller, int bigger) {
        if(smaller<=0) return 0;
        if(smaller==1) return bigger;
        int s=smaller>>1;
        int side=multiplyHelper(s,bigger);
        int side2=side;
        if(smaller%2==1){
            return side+side2+bigger;
        }else{
            return side+side2;
        }
    }

    //8.4
    static List<List<Integer>> getPowerSet(List<Integer> list){
        List<List<Integer>> lists=new ArrayList<>();
        getPowerSet(list,0,lists);
        return lists;
    }

    private static void getPowerSet(List<Integer> list, int index, List<List<Integer>> lists) {
        if(index>=list.size()) return;
        List<Integer> currentList=new ArrayList<>();
        currentList.add(list.get(index));
        int size=lists.size();
        for (int i = 0; i < size; i++) {
            List<Integer> oldList=lists.get(i);
            List<Integer> newList=new ArrayList<>();
            newList.addAll(currentList);
            newList.addAll(oldList);
            lists.add(newList);
        }
        lists.add(currentList);
        getPowerSet(list,index+1,lists);
    }

    //8.3
    static int magicIndex(int[] array){
        return magicIndex(array,0,array.length-1);
//        return magicIndex2(array,0,array.length-1);
    }

    //follow-up
    private static int magicIndex2(int[] array, int p, int r) {
        if(p>r) return -1;
        int midIndex=(p+r)/2;
        int midValue=array[midIndex];
        if(midIndex==array[midIndex]) return midIndex;

        int leftIndex=Math.min(midIndex-1,midValue);
        int leftValue=magicIndex2(array,p,leftIndex);
        if(leftIndex>-1) return leftValue;

        int rightIndex=Math.max(midIndex+1,midValue);
        return magicIndex2(array,rightIndex,r);
    }
    private static int magicIndex(int[] array, int p, int r) {
        if(p>r) return -1;
        int q=(r+p)/2;
        if(array[q]==q) return q;
        else if(array[q]>q){
            return magicIndex(array,p,q-1);
        }else{
            return magicIndex(array,q+1,r);
        }
    }

    //8.2
    static List<Point> getPath(boolean[][] grid){
        List<Point> list=new ArrayList<>();
        HashSet<Point> set=new HashSet<>();
        boolean flag=getPath(grid,new Point(0,0),list,set);
        if(!flag) return null;
        return list;
    }

    private static boolean getPath(boolean[][] grid, Point point, List<Point> list, HashSet<Point> failedPoints) {
        if(failedPoints.contains(point)) return false;
        if(grid[point.row][point.column]==true){
//            list.add(point);
            if(point.row== grid.length-1 && point.column==grid[0].length-1) {
                list.add(point);
                return true;
            }
            boolean right=false;
            boolean down=false;
            if(point.row<grid.length-1) {
                Point d=new Point(point.row+1, point.column);
                down=getPath(grid,d,list,failedPoints);
                if(down) {
                    list.add(point);
                    return true;
                }
            }
            if(point.column<grid[0].length-1) {
                Point r=new Point(point.row, point.column+1);
                right=getPath(grid,r,list,failedPoints);
                if(right) {
                    list.add(point);
                    return true;
                }
            }
        }
        failedPoints.add(point);
        return false;
    }

    //8.1
    static int getNumStep2(int n,int[] result){
        if(n<0) return 0;
        if(n==0) return 1;
        if(result[n]==0){
            result[n]=getNumStep(n-1,result)+getNumStep(n-2,result)+getNumStep(n-3,result);
        }
        return result[n];
    }

    //8.1
    static int getNumStep(int n,int[] result){
        if(n<=0) return 0;
        if(n==1) return 1;
        if(n==2) return 2;
        if(n==3) return 4;
        if(result[n]==0){
            result[n]=getNumStep(n-1,result)+getNumStep(n-2,result)+getNumStep(n-3,result);
        }
        return result[n];
    }

    static int fibonacci(int n){
        if(n<=0) return 0;
        if(n==1) return 1;
        return fibonacci(n-1)+fibonacci(n-2);
    }

    //top-down Dynamic programming
    static int fibonacci(int n,int[] result){
        if(n==0 || n==1) return n;

        if(result[n]==0){
            result[n]=fibonacci(n-1,result)+fibonacci(n-2,result);
        }
        return result[n];
    }

    //bottom-up Dynamic Programming
    static int fibo(int n){
        if(n==0) return n;
        if(n==1) return 1;
        int[] result=new int[n];
        result[0]=0;
        result[1]=1;
        for (int i = 2; i < n; i++) {
            result[i]=result[i-1]+result[i+2];
        }
        return result[n-1]+result[n-2];
    }
}
class Point{
    int row;
    int column;
    public Point(int r, int c){
        row=r;
        column=c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return row == point.row && column == point.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}

class Tower{
    int index;
    Stack<Integer> stack;
    public Tower(int i){
        stack=new Stack<>();
        index=i;
    }
    boolean add(int i){
        if(stack.isEmpty()){
            stack.push(i);
        }else{
            if(stack.peek()<i){
                System.out.println("error");
                return false;
            }else{
                stack.push(i);
            }
        }
        return true;
    }
    boolean moveTo(Tower t){
        if(!stack.isEmpty()){
            int v=stack.peek();
            if(t.add(v)) {
                stack.pop();
                return true;
            }
        }
        return false;
    }
}

class Color{
    String name;
    public Color(String name){
        this.name=name;
    }
}
class MyPoint{
    int row;
    int column;
    public MyPoint(int row, int column){
        this.row=row;
        this.column=column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPoint myPoint = (MyPoint) o;
        return row == myPoint.row && column == myPoint.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}

class Coin{
    int quarters;
    int dimes;
    int nickels;
    int pennies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return quarters == coin.quarters && dimes == coin.dimes && nickels == coin.nickels && pennies == coin.pennies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quarters, dimes, nickels, pennies);
    }

    public Coin(int quarters, int dimes, int nickels, int pennies) {
        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;

    }
}
class Box implements Comparator<Box>{
    int width;
    int depth;
    int height;

    public Box(int width, int depth, int height) {
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

    public Box() {

    }
    @Override
    public int compare(Box o1, Box o2) {
        return o2.height-o1.height;
    }
}