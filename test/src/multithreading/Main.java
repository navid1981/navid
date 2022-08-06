package multithreading;

public class Main {

    public static void main(String[] args){
        MyThread t1=new MyThread("T1");
        MyThread2 t2=new MyThread2("T2");

        t1.start();
        t2.start();
    }
}
