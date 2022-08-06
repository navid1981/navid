package Data;

import Data.Node;

public class CircularLinkedList {
    Node head=null,tail=null;

    public void add(int value){
        Node node=new Node(value);
        if(head==null){
            head=node;
        }else{
            tail.next=node;
        }
        node.next=head;
        tail=node;
    }

    public Node remove(){
        if(head==null){
            return null;
        }
        Node node=head;
        if(head.next==null){
            head=null;
            tail=null;
        }else{
            head=head.next;
            tail.next=head;
        }
        return node;
    }

}
