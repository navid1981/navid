package test;

import java.util.*;

public class TreeGraphTest {
    public static void main(String[] args){
        GNode g1 = new GNode(1);
        GNode g2 = new GNode(2);
        GNode g3 = new GNode(3);
        GNode g4 = new GNode(4);
        GNode g5 = new GNode(5);
        GNode g6 = new GNode(6);
        GNode g7 = new GNode(7);

        GNode[] ga1 = {g2, g5};
        GNode[] ga2 = {g4, g5, g6};
        GNode[] ga3 = {g4, g5};
        GNode[] ga4 = {g7};
        GNode[] ga5 = {};
        GNode[] ga6 = {g7};
        GNode[] ga7 = {};

        g1.adjacents = ga1;
        g2.adjacents = ga2;
        g3.adjacents = ga3;
        g4.adjacents = ga4;
        g5.adjacents = ga5;
        g6.adjacents = ga6;
        g7.adjacents = ga7;

        GNode[] gn = {g1, g2, g3, g4, g5, g6, g7};
        Graph graph = new Graph(gn);

        //4.1
//        System.out.println(isRouteBetweenNodes(g4,g6));

        int[] arr = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        Chapter4.BinaryTree bt=getBSTFromArray(arr);
//        bt.displayBinaryTree();
        Chapter4.BTNode nn1=new Chapter4.BTNode(2);
        Chapter4.BTNode nn2=new Chapter4.BTNode(1);
        bt.root.left.left.left.left=nn2;
        nn2.left=nn1;
//        bt.displayBinaryTree();
//        System.out.println(isBalance(bt.root));

//        System.out.println(validBTS(bt.root,Integer.MIN_VALUE,Integer.MAX_VALUE));

        //4.7
        Project a=new Project("a",4);
        Project b=new Project("b",6);
        Project c=new Project("c",2);
        Project d=new Project("d",1);
        Project e=new Project("e",3);
        Project f=new Project("f",5);
        Project g=new Project("g",6);
        Project[] projects={a,b,c,d,e,f,g};
        Projects p=new Projects(projects);
        Project[][] dependencies={{a,d},{f,b},{b,d},{f,a},{d,c},{a,g}};
//        findProjectPath(p,dependencies);

        Chapter4.BTNode bt1 = new Chapter4.BTNode(4);
        Chapter4.BTNode bt2 = new Chapter4.BTNode(2);
        Chapter4.BTNode bt3 = new Chapter4.BTNode(6);
        Chapter4.BTNode bt4 = new Chapter4.BTNode(3);
        Chapter4.BTNode bt5 = new Chapter4.BTNode(1);
        Chapter4.BTNode bt6 = new Chapter4.BTNode(7);
        Chapter4.BTNode bt7 = new Chapter4.BTNode(5);

        bt1.left = bt2;
        bt1.right = bt3;
        bt2.parent = bt1;
        bt3.parent = bt1;

        bt2.right = bt4;
        bt2.left = bt5;
        bt4.parent = bt2;
        bt5.parent = bt2;

        bt3.right = bt6;
        bt3.left = bt7;
        bt6.parent = bt3;
        bt7.parent = bt3;
        //4.8
//        System.out.println(getCommonAncestor(bt1,bt6,bt7));

        //4.10
//        System.out.println(isSubTree(bt1,bt4));

        BTNode n1=new BTNode(-1);
        BTNode n2=new BTNode(4);
        BTNode n3=new BTNode(2);
        BTNode n4=new BTNode(0);
        BTNode n5=new BTNode(1);
        BTNode n6=new BTNode(-2);
        BTNode n7=new BTNode(3);
        BTNode n8=new BTNode(2);
        n1.left=n2; n2.left=n3; n2.right=n4; n3.left=n5; n3.right=n6; n4.left=n7; n4.right=n8;
        System.out.println(numberOfSum(n1,3));
    }

    //4.12
    static int numberOfSum(BTNode bt, int sum){
        Hashtable<Integer,Integer> hashtable=new Hashtable<>();
        return numberOfSum(bt,sum,hashtable,0);
    }

    private static int numberOfSum(BTNode bt, int sum, Hashtable<Integer, Integer> hashtable, int runningSum) {
        if(bt==null){
            return 0;
        }
        int result=0;
        runningSum=runningSum+bt.value;
        if(runningSum==sum) result++;
        modify(hashtable,runningSum,true);
        if(hashtable.containsKey(runningSum-sum)){
            result+=hashtable.get(runningSum-sum);
        }
        result+=numberOfSum(bt.right,sum,hashtable,runningSum)+numberOfSum(bt.left,sum,hashtable,runningSum);
        modify(hashtable,runningSum,false);
        return result;
    }

    private static void modify(Hashtable<Integer, Integer> hashtable, int runningSum, boolean b) {
        if(b){
            if(hashtable.containsKey(runningSum)){
                hashtable.put(runningSum,hashtable.get(runningSum)+1);
            }else{
                hashtable.put(runningSum,1);
            }
        }else{
            if(hashtable.get(runningSum)==1){
                hashtable.remove(runningSum);
            }else{
                hashtable.put(runningSum,hashtable.get(runningSum)-1);
            }
        }
    }

    //4.10
    static boolean isSubTree(Chapter4.BTNode bt1, Chapter4.BTNode bt2){
        if(bt1==null) return false;
        if(bt1==bt2){
            return isEqual(bt1,bt2);
        }else{
            boolean left=isSubTree(bt1.left,bt2);
            if(left) return left;
            else{
                return isSubTree(bt1.right,bt2);
            }
        }
    }

    private static boolean isEqual(Chapter4.BTNode bt1, Chapter4.BTNode bt2) {
        if(bt1==null&&bt2==null){
            return true;
        }else{
            if(bt1!=bt2) return false;
            return isEqual(bt1.left,bt2.left) && isEqual(bt1.right,bt2.right);
        }
    }

    //4.8
    static Chapter4.BTNode getCommonAncestor(Chapter4.BTNode root, Chapter4.BTNode n1, Chapter4.BTNode n2){
        if(root==null){
            return null;
        }else if(root==n1 || root==n2){
            return root;
        }
        Chapter4.BTNode left=getCommonAncestor(root.left,n1,n2);
        Chapter4.BTNode right=getCommonAncestor(root.right,n1,n2);
        if(left!=null && right!=null) return root;
        else if(left!=null) return left;
        else return right;
    }

    //4.7
    static void findProjectPath(Projects projects, Project[][] list){
        projects.addDependencies(list);
        PriorityQueue<Project> queue=new PriorityQueue<>(new Project());
        for (int i = 0; i < projects.projects.size(); i++) {
            if(projects.projects.get(i).dSize==0) queue.add(projects.projects.get(i));
        }
        int count=0;
        while(!queue.isEmpty()){
            Project p= queue.remove();
            count++;
            System.out.print(p.name+", ");
            List<Project> dependencies=p.dependencies;
            for (int i = 0; i < dependencies.size(); i++) {
                Project c= dependencies.get(i);
                c.dSize--;
                if(c.dSize==0) {
                    c.totalValue+=p.totalValue;
                    queue.add(c);
                }
            }
        }
        if(projects.projects.size()==count) System.out.println(true);
    }
    //4.6
    static Chapter4.BTNode findNextNode(Chapter4.BTNode bt){
        if(bt!=null && bt.right!=null ){
            Chapter4.BTNode current=bt.right;
            while(current.left!=null){
                current=current.left;
            }
            return current;
        }else{
            Chapter4.BTNode parent=bt.parent;
            Chapter4.BTNode child=bt;
            while(parent != null && parent.left!=child){
                parent=parent.parent;
                child=parent;
            }
            return parent;
        }
    }
    //4.5
    static boolean validBTS(Chapter4.BTNode bt, int min, int max){
        if(bt==null) return true;
        if(bt.value<=max && bt.value>min){
            return validBTS(bt.left,min, bt.value) && validBTS(bt.right, bt.value, max);
        }else{
            return false;
        }
    }

    //4.4
    static boolean isBalance(Chapter4.BTNode bt){
        int left=getHigth(bt.left);
        int right=getHigth(bt.right);
        if(Math.abs(left-right)<=1){
            return true;
        }
        return false;
    }

    private static int getHigth(Chapter4.BTNode node) {
        if(node==null) return 0;
        else{
            int left=getHigth(node.left)+1;
            if(left==Integer.MAX_VALUE) return left;
            int right=getHigth(node.right)+1;
            if(right==Integer.MAX_VALUE) return right;
            if(Math.abs(left-right)>1){
                return Integer.MAX_VALUE;
            }else{
                return Math.max(left,right);
            }
        }
    }

    //4.2
    private static Chapter4.BinaryTree getBSTFromArray(int[] arr) {
        Chapter4.BinaryTree bt=new Chapter4.BinaryTree();
        Chapter4.BTNode btNode=getBTNode(arr,0,arr.length-1);
        bt.root=btNode;
        return bt;
    }

    private static Chapter4.BTNode getBTNode(int[] arr, int i, int i1) {
        if(i<=i1){
            int index=(i+i1)/2;
            Chapter4.BTNode btNode=new Chapter4.BTNode();
            btNode.value=arr[index];
            btNode.left=getBTNode(arr,i,index-1);
            btNode.right=getBTNode(arr,index+1,i1);
            return btNode;
        }
        return null;
    }
    //4.1
    private static boolean isRouteBetweenNodes(GNode g1, GNode g6) {
        Queue<GNode> queue=new LinkedList<>();
        queue.add(g1);
        g1.visited=true;
        while(!queue.isEmpty()){
            GNode current=queue.remove();
            if(current==g6) return true;
            GNode[] nodes= current.adjacents;
            for (GNode n: nodes) {
                if(n.visited==false){
                    n.visited=true;
                    queue.add(n);
                }
            }
        }
        return false;
    }
}
class Projects{
    List<Project> projects=null;
    public Projects(Project[] projects){
        this.projects=Arrays.asList(projects);
    }
    public void addDependencies(Project[][] list){
        for (int i = 0; i < list.length; i++) {
            Project[] projects=list[i];
            if(this.projects.contains(projects[0])){
                projects[0].addDpendency(projects[1]);
            }
        }
    }
}
class Project implements Comparator<Project> {
    String name;
    int value;
    int totalValue;
    int dSize;
    List<Project> dependencies=null;
    public Project(String name, int value){
        dependencies=new ArrayList<>();
        this.name=name;
        this.value=value;
        this.totalValue=value;
        dSize=0;
    }
    public Project(){

    }
    public void addDpendency(Project p){
        p.dSize++;
        dependencies.add(p);
    }

    @Override
    public int compare(Project o1, Project o2) {
        if(o1.totalValue< o2.totalValue)return -1;
        else if(o1.totalValue< o2.totalValue) return 1;
        return 0;
    }
}
class BinaryTree{
    BTNode root;
    public BinaryTree(BTNode bt){
        root=bt;
    }
    public BTNode getRandom(){
        int size= root.size;
        int ran=new Random().nextInt(size);
        return getRandom(root,ran);
    }

    private BTNode getRandom(BTNode node, int ran) {
        if(ran==0)return node;
        if(ran<node.left.size) return getRandom(node.left, ran);
        else{
            return getRandom(node.right,ran-node.left.size-1);
        }
    }

    public void insert(BTNode bt){
        BTNode current=root;
        while(current!=null){
            if(bt.value>current.value){
                if(current.right!=null){
                    current.size++;
                    current=current.right;
                }else{
                    current.right=bt;
                }
            }else{
                if(current.left!=null){
                    current.size++;
                    current=current.left;
                }else{
                    current.left=bt;
                }
            }
        }
    }

}
class BTNode{
    int value;
    BTNode left;
    BTNode right;
    int size;
    public BTNode(int val){
        this.value=val;
        size=1;
    }
}
class Graph {
    public GNode[] adjacents;
    public int value;
    public boolean visited;
    public Graph(GNode[] adjacents){
        this.adjacents=adjacents;
    }
}
class GNode {
    public int value;
    public GNode[] adjacents;
    public boolean visited;
    public int dependants;
    public GNode(int value){
        this.value=value;
    }
}