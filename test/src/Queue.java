public class Queue {

    private int size;
    private int front=0;
    private int rear=-1;
    private int item=0;
    private String[] array;

    public Queue(int size){
        this.size=size;
        array=new String[size];
    }

    public boolean isFull(){
        if(item==size) return true;
        return false;
    }

    public boolean isEmpty(){
        if(item==0) return true;
        return false;
    }

    public void insert(String s){
        if(isFull()){
            System.out.println("Queue is full");
            return ;
        }else{
            item++;
            array[front]=s;
            front++;
            if(front==size){
                front=0;
            }
        }
    }

    public String remove(){
        if(isEmpty()){
//            System.out.println("Queue is empty");
            return "Queue is empty";
        }else{
            item--;
            rear++;
            if(rear==size){
                rear=0;
            }
            return  array[rear];
        }
    }

    public void print(){
        int num=item;
        int rear=this.rear+1;
        while (num>0){
            if(rear==size) rear=0;
            System.out.print(array[rear]+", ");
            num--;
            rear++;

        }
    }
}
