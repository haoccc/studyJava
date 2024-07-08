package study;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println("sss");
                log.debug(String.valueOf(Thread.currentThread().getState()));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        Thread.sleep(500);
        System.out.println(thread1.getState());

    }
}
