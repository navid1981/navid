

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;
import java.util.Queue;

public class TreeGraph {

    //4.1
    public static boolean isRouteBetweenNodes(GNode n1, GNode n2){
        Queue<GNode> queue=new LinkedList<>();
        List<GNode> visited=new ArrayList<>();
        queue.add(n1);
        visited.add(n1);
        while(!queue.isEmpty()){
            GNode node=queue.poll();
            if(node==n2){
                return true;
            }
            GNode[] adjacents= node.adjacents;
            for (int i=0;i< adjacents.length;i++) {
                if(!visited.contains(adjacents[i])){
                    queue.add(adjacents[i]);
                    visited.add(adjacents[i]);
                }
            }
        }
        return false;
    }
    //4.2
    public static BinaryTree getBSTFromArray(int[] arr, int p, int r){
        while(p<=r) {
            int q = (p + r) / 2;
            BinaryTree bst = new BinaryTree(arr[q]);
            bst.left = getBSTFromArray(arr, p, q-1);
            bst.right = getBSTFromArray(arr, q + 1, r);
            return bst;
        }
        return null;
    }

    //4.3
    public static List<LinkedList<BinaryTree>> getLinkedListFromBT(BinaryTree bt){
        List<LinkedList<BinaryTree>> lists=new ArrayList<>();
        getLinkedListFromBT(bt,lists,0);
        return lists;
    }
    public static void getLinkedListFromBT(BinaryTree bt,List<LinkedList<BinaryTree>> lists, int n){
        if(bt==null){
            return;
        }
        LinkedList<BinaryTree> list=null;
        if(lists.size()<n+1){
            list = new LinkedList<>();
            lists.add(list);
        }else{
            list=lists.get(n);
        }
        list.add(bt);
        getLinkedListFromBT(bt.left, lists, n+1);
        getLinkedListFromBT(bt.right, lists, n+1);
    }
    //4.3 second with bfs
    public static List<LinkedList<BinaryTree>> getLinkedListFromBT2(BinaryTree bt){
        List<LinkedList<BinaryTree>> lists=new ArrayList<>();
        LinkedList<BinaryTree> parent=new LinkedList<>();
        lists.add(parent);
        if(bt!=null) parent.add(bt);
        while (!parent.isEmpty()){
            LinkedList<BinaryTree> list=new LinkedList<>();
            for (int i = 0; i < parent.size(); i++) {
                BinaryTree btl=parent.get(i).left;
                if(btl!=null) list.add(btl);
                BinaryTree btr=parent.get(i).right;
                if(btr!=null) list.add(btr);
            }
            lists.add(list);
            parent=list;
        }
        return lists;
    }

    //4.4
    public static boolean isBalancedBT(BinaryTree bt){
        return getHight(bt)==Integer.MAX_VALUE?false:true;
    }
    public static int getHight(BinaryTree bt){
        if(bt==null){
            return 0;
        }
        int left=getHight(bt.left);
        if(left==Integer.MAX_VALUE) return Integer.MAX_VALUE;
        int right=getHight(bt.right);
        if(right==Integer.MAX_VALUE) return Integer.MAX_VALUE;
        int diff=Math.abs(left-right);
        if(diff>1) return Integer.MAX_VALUE;
        return Math.max(left,right)+1;
    }

    //4.5
    public static boolean validateBST(BinaryTree bt,int min, int max){
        if(bt==null) return true;
        if(!(bt.value<=max) || !(bt.value>min)) return false;
        return (validateBST(bt.left,min,bt.value) && validateBST(bt.right,bt.value,max));
    }

    //4.6
    public static BinaryTree getSuccessor(BinaryTree bt){
        if(bt.right!=null){
            BinaryTree current=bt.right;
            while(current.left!=null){
                current=current.left;
            }
            return current;
        }else {
            BinaryTree parent=bt.parent;
            BinaryTree current=bt;
            while(parent!=null && parent.right==current){
                current=parent;
                parent=parent.parent;
            }
            return parent;
        }
    }

    //4.7
    public static String buildOrder(Project p){

        Queue<PNode> queue=new LinkedList<>();
        for (PNode node:p.list) {
            if(node.dependants==0) queue.add(node);
        }
        StringBuilder sb=new StringBuilder();
        int size=0;
        while(!queue.isEmpty()){
            PNode node=queue.poll();
            sb.append(node.name+", ");
            size++;
            List<PNode> list=node.adjacents;
            for (PNode n:list) {
                n.dependants--;
                if(n.dependants==0) queue.add(n);
            }
        }
        if(size==p.size) return sb.toString();
        else return "error";
    }


    //4.8
    public static BinaryTree getFirstCommonAncestor(BinaryTree root,BinaryTree bt1,BinaryTree bt2){
        if(root==null) return null;
        if(root==bt1 || root==bt2) return root;

        BinaryTree left=getFirstCommonAncestor(root.left,bt1,bt2);
        if(left!=null && left!=bt1 && left!=bt2) return left;
        BinaryTree right=getFirstCommonAncestor(root.right,bt1,bt2);
        if(right!=null && right!=bt1 && right!=bt2) return right;

        if(left!=null && right!=null) return root;
        if(left==null)return right;
        else{
            return left;
        }
    }

    //4.10
    public static boolean isSubTree2(BinaryTree bt1, BinaryTree bt2){
        if(bt1==null) return false;
        if(bt1.value==bt2.value && isEqualTree(bt1,bt2)){
            return true;
        }
        return isSubTree2(bt1.left,bt2) || isSubTree2(bt1.right,bt2);
    }
    private static boolean isEqualTree(BinaryTree bt1, BinaryTree bt2) {
        if(bt1==null && bt2==null) return true;
        if(bt1==null || bt2==null) return false;
        if(bt1.value!=bt2.value) return false;
        return isEqualTree(bt1.left,bt2.left) && isEqualTree(bt1.right,bt2.right);
    }

    public static boolean isSubTree(BinaryTree bt1, BinaryTree bt2){
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        getArray(bt1,list1);
        getArray(bt2,list2);
        boolean result=false;
        for (int i = 0; i < list1.size(); i++) {
            if(list1.get(i)== list2.get(0)){
                if(list1.size()!=i+ list2.size()) continue;
                result=true;
                for (int j = 0; j < list2.size(); j++) {
                    if(list1.get(i+j).intValue()!=list2.get(j).intValue()) {
                        result=false;
                        break;
                    }
                }
            }
        }
        return result;
    }
    private static void getArray(BinaryTree bt1, List<Integer> list){
        if(bt1==null) {
            list.add(Integer.MIN_VALUE);
            return;
        }
        list.add(bt1.value);
        getArray(bt1.left, list);
        getArray(bt1.right, list);
    }

    //4.12
    public int getNumPathWithSum(BinaryTree bt, int sum){
        return getNumPathWithSum(bt,sum,0,new HashMap<Integer,Integer>());
    }
    private int getNumPathWithSum(BinaryTree bt, int sum, int runningSum, HashMap<Integer, Integer> pathCount) {
        if(bt==null){
            return 0;
        }
        runningSum+=bt.value;
        int total=0;
        if(runningSum==sum) total++;
        total+=pathCount.getOrDefault(runningSum-sum,0);
        increment(pathCount,runningSum,+1);
        total+=getNumPathWithSum(bt.left,sum,runningSum,pathCount);
        total+=getNumPathWithSum(bt.right,sum,runningSum,pathCount);
        increment(pathCount,runningSum,-1);
        return total;
    }
    private void increment(HashMap<Integer, Integer> pathCount, int runningSum, int count) {
        int total=pathCount.getOrDefault(runningSum,0)+count;
        if(total==0) pathCount.remove(runningSum);
        else pathCount.put(runningSum,total);
    }


    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

        BinaryTree bt1 = new BinaryTree(4);
        BinaryTree bt2 = new BinaryTree(2);
        BinaryTree bt3 = new BinaryTree(6);
        BinaryTree bt4 = new BinaryTree(3);
        BinaryTree bt5 = new BinaryTree(1);
        BinaryTree bt6 = new BinaryTree(7);
        BinaryTree bt7 = new BinaryTree(5);
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

        //4.2
//        BinaryTree bt=getBSTFromArray(arr,0,arr.length-1);
//        bt.displayBinaryTree();

        //4.3
//        List<LinkedList<BinaryTree>> lists=getLinkedListFromBT(bt1);
//        List<LinkedList<BinaryTree>> lists=getLinkedListFromBT2(bt1);
//        for (int i = 0; i < lists.size(); i++) {
//            LinkedList<BinaryTree> list=lists.get(i);
//            for (int j = 0; j < list.size(); j++) {
//                System.out.print(list.get(j).value+", ");
//            }
//            System.out.println();
//        }

        //4.4
//        BinaryTree bt8 = new BinaryTree(8);
//        BinaryTree bt9 = new BinaryTree(9);
//        bt7.left=bt8;
//        bt8.left=bt9;
        System.out.println(isBalancedBT(bt1));

        //4.5
        bt1.displayBinaryTree();
//        System.out.println(validateBST(bt1,Integer.MIN_VALUE,Integer.MAX_VALUE));

        //4.6
        System.out.println(getSuccessor(bt4).value);

        //4.8
//        System.out.println(getFirstCommonAncestor(bt1,bt2,bt4).value);

        //4.10
//        BinaryTree bt21 = new BinaryTree(2);
//        System.out.println(isSubTree2(bt1,bt3));

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

        GNode[] g = {g1, g2, g3, g4, g5, g6, g7};
        Graph graph = new Graph(g);

        //4.1
//        System.out.println(isRouteBetweenNodes(g1,g6));

        PNode a=new PNode("a");
        PNode b=new PNode("b");
        PNode c=new PNode("c");
        PNode d=new PNode("d");
        PNode e=new PNode("e");
        PNode f=new PNode("f");

        Project p=new Project();
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        p.add(e);
        p.add(f);

        p.addDependencies(a,d);
        p.addDependencies(f,b);
        p.addDependencies(b,d);
        p.addDependencies(f,a);
        p.addDependencies(d,c);
//        p.addDependencies(a,f);

        //4.7
//        System.out.println(buildOrder(p));
    }

    class BTNode{
        int value;
        BTNode left;
        BTNode right;
        int size;
        public BTNode(int value){
            this.value=value;
            this.size=1;
        }
        public void insert(int value){
            size++;
            BTNode bt=new BTNode(value);
            if(this.value>=value){
                if(this.left==null){
                    this.left=bt;
                }else{
                    this.left.insert(value);
                }
            }else{
                if(this.right==null){
                    this.right=bt;
                }else{
                    this.right.insert(value);
                }
            }
        }

        //4.10
        public BTNode getRandom(){
            int leftSize=(this.left==null)?0:this.left.size;
            int random=new Random().nextInt(this.size);
            if(random==leftSize) return this;
            if(random<leftSize) return this.left.getRandom();
            else{
                return this.right.getRandom();
            }
        }
    }

}

class Project {
    public int size=0;
    public List<PNode> list=new ArrayList<>();
    public void add(PNode node){
        list.add(node);
        size++;
    }
    public void addDependencies(PNode n1,PNode n2){
        List<PNode> nodes=n1.adjacents;
        nodes.add(n2);
        n2.dependants++;
    }
}

class PNode {
    public String name;
    public List<PNode> adjacents=new ArrayList<>();
    public int dependants;
    public PNode(String value){
        this.name=value;
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