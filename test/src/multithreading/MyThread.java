package multithreading;

public class MyThread extends Thread{
    private String name;
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.name+": "+i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(this.name+": Interrupted");
            }
        }
    }
    public MyThread(String name){
        this.name=name;
    }
}
