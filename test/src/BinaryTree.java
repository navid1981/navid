import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    BinaryTree left;
    BinaryTree right;
    BinaryTree parent;
    int value;

    public BinaryTree(int value) {
        this.value=value;
    }

    public void displayBinaryTree(){
        Queue<BinaryTree> queue= new LinkedList<>();
        queue.add(this);
        int counter=0;
        int power=0;
        String s="                        ";
        System.out.print(s);
        while (!queue.isEmpty()){
            counter++;
            BinaryTree binaryTree=queue.poll();
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
