package test;

import java.util.*;

public class Chapter2 {
    public static void main(String[] args){
        LinkedNode<Integer> head=new LinkedNode<>(1);
        head.add(5);
        head.add(9);
        head.add(12);
        head.add(9);
        head.add(3);
        head.add(1);
        head.add(20);
//        head.show();

        //2.1
//        removeDuplicate(head);
//        head.show();

        //2.4
//        partition(head,12).show();

        LinkedNode<Integer> l1=new LinkedNode<>(6);
        l1.add(8);
        l1.add(1);
        l1.add(2);
        l1.add(6);

        LinkedNode<Integer> l2=new LinkedNode<>(2);
        l2.add(9);
        l2.next.next=l1.next; //2,1,2,6
        l2.add(5);

        //2.5
//        System.out.println(sumLists(l1,l2));

//        System.out.println(isPalindrome(l1));

        //2.7
//        System.out.println(getIntersection(l1,l2).data);

        //3.6
        Stack<Integer> stack=new Stack<>();
        stack.push(4); stack.push(2); stack.push(6); stack.push(1);
        sortStack(stack);
    }
    //3.6
    static void sortStack(Stack<Integer> stack){
        Stack<Integer> temp=new Stack<>();
        temp.push(stack.pop());
        while(!stack.isEmpty()){
            if(stack.peek()>= temp.peek()){
                temp.push(stack.pop());
            }else{
                int val=stack.pop();
                while(val< temp.peek()){
                    stack.push(temp.pop());
                }
                temp.push(val);
            }
        }
        while(!temp.isEmpty()){
            stack.push(temp.pop());
        }
    }

    //2.7
    static LinkedNode<Integer> getIntersection(LinkedNode<Integer> l1, LinkedNode<Integer> l2){
        LinkedNode<Integer> l1Current=l1;
        LinkedHashSet<LinkedNode<Integer>> set=new LinkedHashSet<>();
        while(l1Current!=null){
            set.add(l1Current);
            l1Current=l1Current.next;
        }

        while(l2!=null){
            if(set.contains(l2)) return l2;
            l2=l2.next;
        }
        return null;
    }

    //2.6
    static boolean isPalindrome(LinkedNode<Integer> list){
        LinkedNode<Integer> reverseList=getReverse(list);
        while(list!=null){
            if(list.data!= reverseList.data)return false;
            list=list.next;
            reverseList=reverseList.next;
        }
        return true;
    }

    private static LinkedNode<Integer> getReverse(LinkedNode<Integer> list) {
        LinkedNode<Integer> reverse=new LinkedNode<>();
        LinkedNode<Integer> current=list;
        while(current!=null){
            LinkedNode<Integer> node=new LinkedNode<>(current.data);
            node.next=reverse;
            reverse=node;
            current=current.next;
        }
        return reverse;
    }

    //2.5
    static int sumLists(LinkedNode<Integer> l1, LinkedNode<Integer> l2){
        int l1Length=0;
        int l2Length=0;
        LinkedNode<Integer> l1Current=l1;
        LinkedNode<Integer> l2Current=l2;
        while(l1Current!=null){
            l1Length++;
            l1Current=l1Current.next;
        }
        while(l2Current!=null){
            l2Length++;
            l2Current=l2Current.next;
        }
        int diff=Math.abs(l1Length-l2Length);
        LinkedList<Integer> diffSum=new LinkedList<>();
        if(l1Length>=l2Length){
            for (int i = 0; i < diff; i++) {
                diffSum.add(l1.data);
                l1=l1.next;
            }
        }else{
            for (int i = 0; i < diff; i++) {
                diffSum.add(l2.data);
                l2=l2.next;
            }
        }
        int sum=0;
        LinkedList<Integer> result=getSum(l1,l2);
        for (int i = 0; i < result.size(); i++) {
            sum+=result.get(i)*Math.pow(10,i);
        }
        int pow=result.size();
        for (int i = diffSum.size()-1; i >-1 ; i--) {
            sum+=diffSum.get(i)*Math.pow(10,pow);
            pow++;
        }
        return sum;
    }

    private static LinkedList<Integer> getSum(LinkedNode<Integer> l1, LinkedNode<Integer> l2) {
        LinkedList<Integer> sum;
        if(l1.next!=null & l2.next!=null) {
            sum=getSum(l1.next,l2.next);
            sum.add(l1.data+l2.data);
        } else{
            sum=new LinkedList<>();
            sum.add(l1.data+l2.data);
        }
        return sum;
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
    //2.1
    static void removeDuplicate(LinkedNode<Integer> list){
        HashSet<Integer> hashSet=new HashSet<>();
        hashSet.add(list.data);
        LinkedNode<Integer> current=list;
        while(current.next!=null){
            if(hashSet.contains(current.next.data)){
                current.next=current.next.next;
            }else{
                hashSet.add(current.next.data);
                current=current.next;
            }
        }
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
    class MyQueue<T>{
        Stack<T> newItems;
        Stack<T> oldItems;
        public MyQueue(){
            newItems=new Stack<>();
            oldItems=new Stack<>();
        }
        public void add(T data){
            newItems.push(data);
        }

        public T remove(){
            if(!oldItems.isEmpty()){
                return oldItems.pop();
            }else{
                while(!newItems.isEmpty()){
                    oldItems.push(newItems.pop());
                }
            }
            return oldItems.pop();
        }

        public T peek(){
            if(!oldItems.isEmpty()){
                return oldItems.peek();
            }else{
                while(!newItems.isEmpty()){
                    oldItems.push(newItems.pop());
                }
            }
            return oldItems.peek();
        }
    }

    class SortedStack<Integer> {
        Stack<Integer> stack;
        Stack<Integer> temp;
        public SortedStack(){
            stack=new Stack<>();
            temp=new Stack<>();
        }
        public void push(Integer data){
            if(stack.isEmpty()) {
                stack.push(data);
                return;
            }
            while((int)data > (int)stack.peek()){
                temp.push(stack.pop());
            }
            stack.push(data);
            while (!temp.isEmpty()){
                stack.push(temp.pop());
            }
        }
    }
}
