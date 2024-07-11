package study;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示java中 线程 的六种状态
 */
//@Slf4j
public class sixStatesDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // new
                System.out.println("t1");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // runnable
                while (true) {
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                // terminated
                System.out.println("t3");
            }
        });

        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                // time waiting
                synchronized (sixStatesDemo.class){
                    try {
                        Thread.sleep(100000000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                // waiting
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Thread t6 = new Thread(new Runnable() {
            @Override
            public void run() {
                // blocked
                synchronized (sixStatesDemo.class){
                    try {
                        Thread.sleep(100000000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

//        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        System.out.println(t1.getState());
        System.out.println(t2.getState());
        System.out.println(t3.getState());
        System.out.println(t4.getState());
        System.out.println(t5.getState());
        System.out.println(t6.getState());

    }
}
