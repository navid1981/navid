package test;

import java.util.*;

public class Chapter4 {

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

        GNode[] myG = {g1, g2, g3, g4, g5, g6, g7};
        Graph graph = new Graph(myG);

        //4.1
//        System.out.println(isRouteBetweenNodes(g2,g7));

        //4.2
        int[] arr = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        BinaryTree bt=getBSTFromArray(arr);
//        bt.displayBinaryTree();

        //4.3
//        List<LinkedList<BTNode>> lists=getLinkedListFromBT(bt);
//        for (int i = 0; i <lists.size() ; i++) {
//            System.out.println();
//            LinkedList<BTNode> list= lists.get(i);
//            for (int j = 0; j < list.size() ; j++) {
//                System.out.print(list.get(j)+", ");
//            }
//        }

        //4.4
//        BTNode n1=new BTNode(2);
//        BTNode n2=new BTNode(1);
//        bt.root.left.left.left.left=n2;
//        n2.left=n1;
//        System.out.println(isBalance(bt));

        //4.5
//        System.out.println(isValidBTS(bt));

        //4.6
//        BTNode bt1 = new BTNode(4);
//        BTNode bt2 = new BTNode(2);
//        BTNode bt3 = new BTNode(6);
//        BTNode bt4 = new BTNode(3);
//        BTNode bt5 = new BTNode(1);
//        BTNode bt6 = new BTNode(7);
//        BTNode bt7 = new BTNode(5);
//        bt1.left = bt2;
//        bt1.right = bt3;
//        bt2.parent = bt1;
//        bt3.parent = bt1;
//
//        bt2.right = bt4;
//        bt2.left = bt5;
//        bt4.parent = bt2;
//        bt5.parent = bt2;
//
//        bt3.right = bt6;
//        bt3.left = bt7;
//        bt6.parent = bt3;
//        bt7.parent = bt3;
//        BinaryTree binaryTree=new BinaryTree();
//        binaryTree.root=bt1;
//        binaryTree.displayBinaryTree();
//        System.out.println(getSuccessor(binaryTree.root.right.left).value);

        //4.7
        Project a=new Project("a",4);
        Project b=new Project("b",6);
        Project c=new Project("c",2);
        Project d=new Project("d",1);
        Project e=new Project("e",3);
        Project f=new Project("f",5);
        Project g=new Project("g",6);
        Project[] projects={a,b,c,d,e,f,g};
        Project[][] dependencies={{a,d},{f,b},{b,d},{f,a},{d,c},{a,g}};
//        buildOrder(projects,dependencies);

        //4.8
//        System.out.println(getFirstCommonAncestor(bt.root,bt.root.left.left.left,bt.root.left.right.left).value);

        //4.10
//        System.out.println(isSubTree(bt.root,bt.root.right.left));

        BTNode n1=new BTNode(-1);
        BTNode n2=new BTNode(4);
        BTNode n3=new BTNode(2);
        BTNode n4=new BTNode(0);
        BTNode n5=new BTNode(1);
        BTNode n6=new BTNode(-2);
        BTNode n7=new BTNode(3);
        BTNode n8=new BTNode(2);
        n1.left=n2; n2.left=n3; n2.right=n4; n3.left=n5; n3.right=n6; n4.left=n7; n4.right=n8;
        System.out.println(getPathsWithSum(n1,3));

    }

    //4.12
    public static int getPathsWithSum(BTNode node, int sum){
        Map<Integer, Integer> map=new HashMap<>();
        return getPathsWithSum(node,sum,map,0);
    }

    private static int getPathsWithSum(BTNode node, int sum, Map<Integer, Integer> map, int value) {
        int count=0;
        if(node!=null) {
            value=node.value + value;
            if(map.containsKey(value)){
                map.put(value,map.get(value)+1);
            }else{
                map.put(value,1);
            }
            if(value==sum) count++;
            if(map.containsKey(value-sum)) count++;
            count=getPathsWithSum(node.left,sum,map,value)+getPathsWithSum(node.right,sum,map,value)+count;
            if(map.get(value)==1){
                map.remove(value);
            }else{
                map.put(value,map.get(value)-1);
            }
        }
        return count;
    }

    //4.10
    public static boolean isSubTree(BTNode node1, BTNode node2){
        BTNode node=getNode(node1,node2);
        if(node!=null){
            return isEqual(node,node2);
        }
        return false;
    }

    private static boolean isEqual(BTNode node, BTNode node2) {
        if(node==null && node2==null) return true;
        if(node!=node2) return false;
        return isEqual(node.left,node2.left) && isEqual(node.right,node2.right);
    }

    private static BTNode getNode(BTNode node1, BTNode node2) {
        if(node1==null) return null;
        if(node1==node2) return node1;
        BTNode left=getNode(node1.left,node2);
        if(left!=null) return left;
        return getNode(node1.right,node2);
    }

    //4.8
    public static BTNode getFirstCommonAncestor(BTNode node, BTNode node1, BTNode node2){
        if(node==null) return null;
        if (node==node1 || node==node2) return node;
        if(getFirstCommonAncestor(node.left,node1,node2)==null){
            return getFirstCommonAncestor(node.right,node1,node2);
        }else if(getFirstCommonAncestor(node.right,node1,node2)==null){
            return getFirstCommonAncestor(node.left,node1,node2);
        }else{
            return node;
        }
    }

    //4.7
    public static void buildOrder(Project[] projects,Project[][] dependencies){
        for (int i = 0; i < dependencies.length; i++) {
            Project[] dependency=dependencies[i];
            dependency[0].addNextProject(dependency[1]);
        }
        PriorityQueue<Project> queue=new PriorityQueue<>(new Project());
        for (int i = 0; i < projects.length; i++) {
            if(projects[i].depSize==0){
                queue.add(projects[i]);
            }
        }
        int size=0;

        while (!queue.isEmpty()){
            Project current= queue.poll();
            System.out.print(current.name+":"+current.totalSize+", ");
            size++;
            List<Project> nextPs=current.nextProjects;

            for (int i = 0; i < nextPs.size(); i++) {
                Project nextP=nextPs.get(i);
                nextP.depSize--;
                if(nextP.depSize==0) {
                    nextP.totalSize+=current.totalSize;
                    queue.add(nextP);
                }
            }
        }
        if(size!= projects.length) System.out.println("no path");
    }

    //4.6
    public static BTNode getSuccessor(BTNode node){
        BTNode current=node;
        if(current.right!=null){
            current=current.right;
            while(current.left!=null){
                current=current.left;
            }
            return current;
        }

        BTNode parent=node.parent;
        while(parent!=null && parent.left!=current){
            current=parent;
            parent=parent.parent;
        }
        return parent;
    }

    //4.5
    public static boolean isValidBTS(BinaryTree bt){
        BTNode node=bt.root;
        return isValidBTS(node,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private static boolean isValidBTS(BTNode node, int minValue, int maxValue) {
        if(node!=null) {
            if (node.value <= maxValue && node.value > minValue) {
                return isValidBTS(node.left,minValue,node.value) && isValidBTS(node.right, node.value, maxValue);
            } else {
                return false;
            }
        }else{
            return true;
        }
    }

    //4.4
    public static boolean isBalance(BinaryTree bt){
        BTNode root=bt.root;

        return gethight(root)!=Integer.MAX_VALUE;
    }

    private static int gethight(BTNode node) {
        if(node!=null){
            int left=gethight(node.left);
            if(left==Integer.MAX_VALUE) return Integer.MAX_VALUE;
            int right=gethight(node.right);
            if(right==Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if(Math.abs(left-right)>1){
                return Integer.MAX_VALUE;
            }
            return Math.max(left,right)+1;
        }
        return 0;
    }

    //4.3
    private static List<LinkedList<BTNode>> getLinkedListFromBT(BinaryTree bt) {
        List<LinkedList<BTNode>> lists=new ArrayList<>();
        getLinkedListFromBT(bt.root,lists,0);
        return lists;
    }

    private static void getLinkedListFromBT(BTNode node, List<LinkedList<BTNode>> lists, int n) {
        if(node!=null){
            if(lists.size()<=n){
                LinkedList<BTNode> list=new LinkedList<>();
                list.add(node);
                lists.add(list);
            }else{
                LinkedList<BTNode> list= lists.get(n);
                list.add(node);
            }
            getLinkedListFromBT(node.left,lists,n+1);
            getLinkedListFromBT(node.right,lists,n+1);
        }
    }

    //4.2
    private static BinaryTree getBSTFromArray(int[] arr) {
        BinaryTree bt=new BinaryTree();
        BTNode btNode=getBTNode(arr,0,arr.length-1);
        bt.root=btNode;
        return bt;
    }

    private static BTNode getBTNode(int[] arr, int i, int i1) {
        if(i<=i1){
            int index=(i+i1)/2;
            BTNode btNode=new BTNode();
            btNode.value=arr[index];
            btNode.left=getBTNode(arr,i,index-1);
            btNode.right=getBTNode(arr,index+1,i1);
            return btNode;
        }
        return null;
    }

    //4.1
    private static boolean isRouteBetweenNodes(GNode g1, GNode g2) {
        Queue<GNode> queue=new LinkedList<>();
        queue.add(g1);
        List<GNode> visited=new ArrayList<>();
        visited.add(g1);
        //Print route
        Map<GNode,String> v=new HashMap<>();
        v.put(g1,g1.value+", ");
        while(!queue.isEmpty()){
            GNode current= queue.poll();
            if(current==g2){
                System.out.println(v.get(current));
                return true;
            }
            GNode[] adjacents= current.adjacents;
            for (int i = 0; i < adjacents.length; i++) {
                GNode adjacent=adjacents[i];
                if(!visited.contains(adjacent)){
                    queue.add(adjacent);
                    visited.add(adjacent);
                    v.put(adjacent,v.get(current)+adjacent.value+", ");
                }

            }
        }
        return false;
    }

    static class GNode {
        int value;
        GNode[] adjacents;
        public GNode(int value){
            this.value=value;
        }
    }
    static class Graph {
        GNode[] adjacents;
        int value;
        boolean visited;
        public Graph(GNode[] adjacents){
            this.adjacents=adjacents;
        }
    }
    static class BinaryTree{
        BTNode root;
        public void displayBinaryTree(){
            Queue<BTNode> queue= new LinkedList<>();
            queue.add(this.root);
            int counter=0;
            int power=0;
            String s="                        ";
            System.out.print(s);
            while (!queue.isEmpty()){
                counter++;
                BTNode binaryTree=queue.poll();
                System.out.print(binaryTree.value+", ");
                if(binaryTree.left!=null)queue.add(binaryTree.left);
                if(binaryTree.right!=null)queue.add(binaryTree.right);
                if(Math.pow(2,power)==counter){
                    power++;
                    System.out.println();
                    System.out.print(s.substring((int)Math.pow(2,power)));
                    counter=0;
                }
            }
            System.out.println();
        }
    }
    static class BTNode{
        int value;
        int size;
        BTNode left;
        BTNode right;
        BTNode parent;
        public BTNode(int value) {
            this.value = value;
        }

        public BTNode() {
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    static class Project implements Comparator<Project>{
        String name;
        int size;
        int totalSize;
        List<Project> nextProjects=new ArrayList<>();
        int depSize;
        public void addNextProject(Project p){
            p.depSize++;
            nextProjects.add(p);
        }

        public Project(String name) {
            this.name = name;
        }

        public Project() {
        }

        public Project(String name, int size) {
            this.name = name;
            this.size = size;
            this.totalSize=size;
        }

        @Override
        public int compare(Project o1, Project o2) {
            if (o1.totalSize < o2.totalSize) return -1;
            if (o1.totalSize > o2.totalSize) return 1;
            return 0;
        }
    }
}
