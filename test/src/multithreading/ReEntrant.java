package multithreading;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrant {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting ....");
        cond.await();
        System.out.println("Woken up!");
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");
        cond.signal();
        try {
            increment();
        } finally {
            //should be written to unlock Thread whenever signal() is called
            lock.unlock();
        }

    }

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while (true) {
            // Acquire locks
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;
            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            } finally {
                if (gotFirstLock && gotSecondLock) return;
                else if (gotFirstLock) firstLock.unlock();
                else if (gotSecondLock) secondLock.unlock();
            }
            // Locks not acquired
            Thread.sleep(1);
        }
    }

    public void firstThread1() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);
            try {
//                Account.transfer(acc1, acc2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

}