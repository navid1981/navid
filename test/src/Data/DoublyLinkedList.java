package Data;

import Data.Node;

public class DoublyLinkedList {
    private Node head;
    private Node tail;

    public boolean isEmpty(){
        if(head==null)return true;
        return false;
    }
    public void addLast(int value){
        Node node=new Node(value);
        if(head==null){
            head=node;
            tail=node;
        }else{
            tail.next=node;
            node.previous=tail;
            tail=node;
        }
    }
    public void addFirst(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        }else{
            node.next=head;
            head.previous=node;
            head=node;
        }
    }

    public void removeFirst(){
        if (head == null) return;
        if(head.next==null){
            head=null;
            tail=null;
        }else{
            head.next.previous=null;
            head=head.next;
        }
    }

    public void removeLast(){
        if (head == null) return;
        if(head.next==null){
            head=null;
            tail=null;
        }else{
            tail.previous.next=null;
            tail=tail.previous;
        }
    }

    public boolean deleteKey(int k){
        if(head==null) return false;
        if(head.next==null){
            if(head.value==k) {
                head=null; tail=null;
                return true;
            }
            return false;
        }
        if(head.value==k){
            head.next.previous=null;
            head=head.next;
            return true;
        }
        if(tail.value==k){
            tail.previous.next=null;
            tail=tail.previous;
            return true;
        }
        Node current=head.next;
        do{
            if(current.value==k){
                current.next.previous=current.previous;
                current.previous.next=current.next;
                return true;
            }
            current=current.next;
        }while(current!=tail);
        return false;
    }

    public void display(){
        Node current=head;
        while(current!=null){
            System.out.print(current.value+", ");
            current=current.next;
        }
        System.out.println("");
    }
}
