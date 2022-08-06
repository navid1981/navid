package Data;

public class MyStack {

    private String[] array;
    private int top=-1;
    private int size;

    public MyStack(int size) {
        this.size=size;
        array=new String[size];
    }

    public boolean isEmpty(){
        if(top==-1) return true;
        return false;
    }

    public boolean isFull(){
        if(top==size-1) return true;
        return false;
    }

    public void push(String s){
        if(isFull()) System.out.println("Stack is full");
        else{
            top++;
            array[top]=s;
        }
    }

    public String pop(){
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        else{
            String s=array[top];
            top--;
            return s;
        }
    }

    public String peak(){
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        else{
            return array[top];
        }
    }
}
