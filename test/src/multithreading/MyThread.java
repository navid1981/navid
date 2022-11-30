package multithreading;

import java.util.concurrent.CountDownLatch;

public class MyThread extends Thread{
    private String name;
    CountDownLatch countDownLatch;
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.name+": "+i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(this.name+": Interrupted");
            }
            countDownLatch.countDown();
        }
    }
    public MyThread(String name, CountDownLatch countDownLatch){
        this.name=name;
        this.countDownLatch=countDownLatch;
    }
}
