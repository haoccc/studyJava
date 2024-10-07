package study.eight;

import lombok.val;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(4);

        for (int i = 0; i < 10; i++) {
            int x = i;
            new Thread(() ->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("获得信号量:" + x);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println("------释放信号量:" + x);
                    semaphore.release();
                }
            }).start();
        }
    }
}
