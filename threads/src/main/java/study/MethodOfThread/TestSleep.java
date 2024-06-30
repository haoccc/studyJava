package study.MethodOfThread;


/**
 * 模拟网络延时(放大问题的发生性：线程不安全)
 * 每个对象都有一把锁，sleep不会释放锁
 */
public class TestSleep implements Runnable{

    // 设置一个停止的标志位
    private int num = 100;

    @Override
    public void run() {
        while (true){
            if (num<=0) break;
            //模拟时延
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "拿到了第：" + num--);
        }
    }


    public static void main(String[] args) {
        TestSleep testStop = new TestSleep();
        Thread thread = new Thread(testStop, "小明");
        Thread thread2 = new Thread(testStop, "大熊");
        Thread thread3 = new Thread(testStop, "老师");

        thread.start();
        thread2.start();
        thread3.start();
    }
}
