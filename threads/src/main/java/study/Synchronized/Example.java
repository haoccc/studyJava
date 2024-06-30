package study.Synchronized;

import java.util.ArrayList;
import java.util.List;

public class Example implements Runnable {
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

    private void buy() throws InterruptedException {
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


    public static void main(String[] args) {
        // 买票
        Example example = new Example();
        Thread thread = new Thread(example, "黄牛");
        Thread thread2 = new Thread(example, "你");
        Thread thread3 = new Thread(example, "我");
        thread.start();
        thread2.start();
        thread3.start();

        // 集合不安全
        List<String> list = new ArrayList<>();
        // 两个线程同时操作了一个 数组的位置
        for (int i=0;i<10000;i++){
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        System.out.println("集合大小:" + list.size());
    }
}
