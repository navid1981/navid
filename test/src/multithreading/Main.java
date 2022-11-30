package multithreading;

import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args){
        CountDownLatch latch = new CountDownLatch(3);

        MyThread t1=new MyThread("T1",latch);
        MyThread2 t2=new MyThread2("T2");

//        t1.start();
//        t2.start();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            executor.submit(new MyThread("T1",latch));
        }
        executor.shutdown();

        try {
            // Application main thread waits, till other service threads which are
            // as an example responsible for starting framework services have completed started all services.
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed.");



        Future<Integer> future = executor.submit(new Callable<Integer>() {

            @Override
            //return value is Integer
            public Integer call() throws TimeoutException {
                Random random = new Random();
                int duration = random.nextInt(4000);
                if (duration > 2000) {
                    throw new TimeoutException ("Sleeping for too long.");
                }

                System.out.println("Starting ...");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException ignored) {}
                System.out.println("Finished.");
                return duration;
            }
        });

        try {
            int val=future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

