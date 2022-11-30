import Data.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Chapter2 {

    public static void main(String[] args){
        LinkedNode<Integer> head=new LinkedNode<>(1);
        head.add(5);
        head.add(9);
        head.add(12);
//        head.add(4);
//        head.add(2);
//        head.show();

        //2.1
//        removeDups(head);
//        head.show();

        //2.2
//        System.out.println(returnKthToLast(head,1).data);

        //2.3
//        removeMiddleNode(head);
//        head.show();

        //2.5
        LinkedNode<Integer> a1=new LinkedNode<>(7); a1.add(1); a1.add(1); a1.add(7);
        LinkedNode<Integer> a2=new LinkedNode<>(2); a2.add(9); a2.add(5);
//        sum(a1,a2);

        //2.6
//        System.out.println(isPalindrome(a1));
    }

    //2.6
    static boolean isPalindrome(LinkedNode<Integer> list){
        Stack<Integer> s1=new Stack<>();
        LinkedNode<Integer> current=list;
        while(current!= null){
            s1.push(current.data);
            current=current.next;
        }
        current=list;
        while (current!=null){
            if(current.data!=s1.pop()){
                return false;
            }
            current=current.next;
        }
        return true;
    }

    //2.5
    public static void sum(LinkedNode<Integer> a1, LinkedNode<Integer> a2){
        Stack<Integer> s1=new Stack<Integer>();
        Stack<Integer> s2=new Stack<Integer>();
        Stack<Integer> resultS=new Stack<Integer>();
        LinkedNode<Integer> result=new LinkedNode<>();
        while (a1!=null){
            s1.push(a1.data);
            a1=a1.next;
        }
        while (a2!=null){
            s2.push(a2.data);
            a2=a2.next;
        }
        int carry=0;
        while(!s1.isEmpty() || !s2.isEmpty()){
            int add=0;
            if(!s1.isEmpty() && !s2.isEmpty()){
                add=s1.pop()+s2.pop()+carry;

            }else if(!s1.isEmpty()){
                add=s1.pop()+carry;
            }else{
                add=s2.pop()+carry;
            }
            carry=0;
            if(add>9){
                carry=1;
                add=add%10;
            }
            resultS.add(add);
        }
        while(!resultS.isEmpty()){
            result.add(resultS.pop());
        }
        result.show();
    }

    //2.4
    static LinkedNode<Integer> partition(LinkedNode<Integer> list, int x){
        LinkedNode<Integer> leftList=new LinkedNode<>();
        LinkedNode<Integer> rightList=new LinkedNode<>();
        LinkedNode<Integer> current=list;
        LinkedNode<Integer> leftListTail=null;
        while(current!=null){
            if(current.data<x){
                leftListTail=leftList.add(current.data);
            }else{
                rightList.add(current.data);
            }
            current=current.next;
        }
        if(leftListTail!=null) {
            leftListTail.next = rightList;
            return leftList;
        }
        return rightList;
    }

    //2.3
    //1-5-9-12
    public static void removeMiddleNode(LinkedNode<Integer> head){
        if(head.next.next==null) return;
        LinkedNode<Integer> node=head.next.next;
        LinkedNode<Integer> nodeSecond=head;
        int counter=1;
        while (node!=null){
            if(counter%2==0){
                nodeSecond=nodeSecond.next;
            }
            node=node.next;
            counter++;
        }
        nodeSecond.next=nodeSecond.next.next;

    }

    //2.1
    public static void removeDups(LinkedNode<Integer> head){
        LinkedNode<Integer> node=head;
       while(node!=null){
           int val=node.data;
           LinkedNode<Integer> current=node;
           while(current.next!=null){
               if(current.next.data==val){
                   current.next=current.next.next;
               }else{
                   current=current.next;
               }
           }
           node=node.next;
       }
    }

    //2.2
    public static LinkedNode<Integer> returnKthToLast(LinkedNode<Integer> head,int k){
        LinkedNode<Integer> node=head;
        LinkedNode<Integer> nodeSecond=head;
        int counter=0;
        while(node!=null){
            if(counter>k){
                nodeSecond=nodeSecond.next;
            }
            node=node.next;
            counter++;
        }
        return nodeSecond;
    }

    static class LinkedNode<T>{
        LinkedNode<T> next=null;
        T data=null;
        public LinkedNode(T data){
            this.data=data;
        }
        public LinkedNode<T> add(T data){
            if(this.data==null){
                this.data=data;
                return this;
            }
            LinkedNode<T> node=new LinkedNode<>(data);
            LinkedNode<T> current=this;
            while(current.next!=null){
                current=current.next;
            }
            current.next=node;
            return node;
        }

        public LinkedNode<T> addFirst(T data){
            if(this.data==null){
                this.data=data;
                return this;
            }
            LinkedNode<T> node=new LinkedNode<>(data);
            node.next=this;

            return node;
        }

        public LinkedNode() {
        }

        public LinkedNode<T> delete(LinkedNode<T> head , T data){
            if(head.data==data){
                return head.next;
            }
            LinkedNode<T> current=head;
            while(current.next!=null){
                if(current.next.data==data){
                    current.next=current.next.next;
                }
                current=current.next;
            }
            return head;
        }

        public void show(){
            LinkedNode<T> node=this;
            do {
                System.out.print(node.data+", ");
                node=node.next;
            }while(node!=null);
            System.out.println();
        }
    }
}
