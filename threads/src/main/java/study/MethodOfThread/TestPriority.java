package study.MethodOfThread;


import java.sql.SQLOutput;

/**
 * 先设置优先级再启动
 *
 * 性能倒置：cpu执行了优先级低的
 */
public class TestPriority implements Runnable{
    @Override
    public void run(){
//        System.out.println(Thread.currentThread().getName()+":线程开始执行");
//        Thread.yield();
//        System.out.println(Thread.currentThread().getName()+":线程停止");
//        for (int i=0; i<100; i++){
//            System.out.println(i);
//        }
        System.out.println(Thread.currentThread().getName() + "--->" + Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        // 主线程的默认优先级
        System.out.println(Thread.currentThread().getName() + "--->" + Thread.currentThread().getPriority());
        TestPriority testPriority = new TestPriority();

        Thread t1 = new Thread(testPriority);
        Thread t2 = new Thread(testPriority);
        Thread t3 = new Thread(testPriority);
        Thread t4 = new Thread(testPriority);

        t1.setPriority(3);
        t1.start();

        t2.setPriority(10);
        t2.start();

        t3.setPriority(1);
        t3.start();

        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();
    }
}
