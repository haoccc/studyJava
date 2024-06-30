package study.Synchronized;

import java.util.ArrayList;
import java.util.List;

/**
 * 给增删改枷锁
 * 锁变化两
 */
public class SafeExample implements Runnable {
    // 设置一个停止的标志位
    private static int num = 5;
    private static boolean flag = true;

    @Override
    public void run() {
        while (flag){
            try {
                buy();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 同步方法
    private synchronized void buy() throws InterruptedException {
        Thread.sleep(400);
        if (num<=0) {
            flag = false;
            return;
        }
        System.out.println(Thread.currentThread().getName() + "拿到:"+ num--);
    }

    private void stop(){
        flag = false;
    }


    public static void main(String[] args) throws InterruptedException {
        // 买票
//        SafeExample example = new SafeExample();
//        Thread thread = new Thread(example, "黄牛");
//        Thread thread2 = new Thread(example, "你");
//        Thread thread3 = new Thread(example, "我");
//        thread.start();
//        thread2.start();
//        thread3.start();

        // 集合不安全
        List<String> list = new ArrayList<>();
        // 两个线程同时操作了一个 数组的位置
        for (int i=0;i<10000;i++){
            new Thread(()->{
//                synchronized (list){
//                    list.add(Thread.currentThread().getName());
//                }
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(3000);
        System.out.println("集合大小:" + list.size());
    }
}
