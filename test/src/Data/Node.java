package Data;

public class Node {
    public int value;
    public Node next;
    public Node previous;
    int min;
    public void display(){
        System.out.println(value);
    }
    public Node(int value){
        this.value=value;
    }
}
