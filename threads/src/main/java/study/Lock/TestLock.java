package study.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();
        new Thread(testLock2, "黄牛").start();
        new Thread(testLock2, "dean").start();
        new Thread(testLock2, "sam").start();
    }
}


class TestLock2 implements Runnable{
    private int num = 10;
    private final ReentrantLock lock = new ReentrantLock();


    @Override
    public void run(){
        while (true){
            try{
                lock.lock();
                if (num<=0) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "拿到:"+ num--);
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
