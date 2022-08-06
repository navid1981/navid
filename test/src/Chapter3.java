import java.util.LinkedList;
import java.util.Stack;

public class Chapter3 {

    public static void main(String[] arga){
        Stack<Integer> s=new Stack<>();
        s.add(4);
        s.add(7);
        s.add(2);
        s.add(5);
        sortStack(s);
        while(!s.isEmpty()){
            System.out.print(s.pop()+", ");
        }
    }

    //3.5
    public static void sortStack(Stack<Integer> s){
        Stack<Integer> temp=new Stack<>();
        temp.push(s.pop());
        while(!s.isEmpty()){
            int val=s.pop();
            while (!temp.isEmpty() && temp.peek()>val){
                s.push(temp.pop());
            }
            temp.push(val);

        }
        while (!temp.isEmpty()){
            s.push(temp.pop());
        }
    }

    //sorted stack
    public static class MyStack{
        Stack<Integer> main =new Stack<>();
        Stack<Integer> temp =new Stack<>();

        public void push(int data){
            int val= main.peek();
            while(!main.isEmpty() && val<data){
                temp.push(main.pop());
                val=main.peek();
            }
            main.push(data);
            while(!temp.isEmpty()){
                main.push(temp.pop());
            }
        }
    }

    //3.4
    public static class MyQueue<T>{
        Stack<T> newest =new Stack<>();
        Stack<T> oldest =new Stack<>();

        public void add(T data){
            newest.push(data);
        }
        public T remove(){
            if(oldest.isEmpty()) {
                while (!newest.isEmpty()) {
                    oldest.push(newest.pop());
                }
            }
            return oldest.pop();
        }
    }

    public static class QueueNode<T>{
        Node<T> first;
        Node<T> last;
        public void add(T data){
            Node<T> end=new Node<>(data);
            if(first==null){
                first=end;
            }else{
                last.next=end;
            }
            last=end;
        }

        public T remove(){
            Node<T> node=first;
            if(first==null) return null;
            if(first.next==null){
                first=null;
                last=null;
            }else{
                first=first.next;
            }
            return first.data;
        }
    }

    public static class StackNode<T>{
        Node<T> top;

        public void push(T data){
            Node<T> s=new Node<>(data);
            if(data instanceof Integer){
                int value=(Integer) data;
                s.min=Math.min(this.min(),value);
            }
            s.next=top;
            top=s;
        }

        public T pop(){
            Node<T> result=top;
            if(top!=null){
                top=top.next;
            }else{
                return null;
            }
            return result.data;
        }

        public Node<T> peek(){
            if(top!=null) return top;
            else return null;
        }

        public int min(){
            Node<T> node=peek();
            if(node.data instanceof Integer){
                int value=(Integer) node.min;
                return value;
            }
            return Integer.MAX_VALUE;
        }

        public boolean isEmpty(){
            return top==null;
        }

        public void show(){
            Node<T> node=top;
            while(node!=null) {
                System.out.print(node.data+", ");
                node=node.next;
            }
            System.out.println();
        }
    }

    public static class Node<T>{
        T data;
        Node<T> next;
        int min=Integer.MAX_VALUE;

        public Node(T data) {
            this.data = data;
        }
    }
}
class Animal{
    String type;
    int order;
    public Animal(String type){
        this.type=type;
    }

}

//3.6
class AnimalQueue{
    LinkedList<Animal> cats;
    LinkedList<Animal> dogs;
    int order;
    public AnimalQueue(){
        cats=new LinkedList<>();
        dogs=new LinkedList<>();
        order=0;
    }

    public void enqueue(Animal animal){
        animal.order=order;
        if(animal.type.equals("cat")){
            cats.add(animal);
        }else{
            dogs.add(animal);
        }
        order++;
    }
    public Animal dequeueAny(){
        if(cats.size()==0) return dequeueDog();
        else if(dogs.size()==0) return dequeueCat();
        Animal cat=cats.peek();
        Animal dog=dogs.peek();
        if(cat.order< dog.order){
            return dequeueCat();
        }else{
            return dequeueDog();
        }
    }

    public Animal dequeueDog(){
        return dogs.poll();
    }

    public Animal dequeueCat(){
        return cats.poll();
    }
}