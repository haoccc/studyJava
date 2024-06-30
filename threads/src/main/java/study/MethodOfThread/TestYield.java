package study.MethodOfThread;


/**
 * 测试礼让线程
 * 礼让不一定成功
 */
public class TestYield implements Runnable{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+":线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+":线程停止");
    }

    public static void main(String[] args) {
        TestYield testYield = new TestYield();
        Thread thread = new Thread(testYield, "a");
        Thread thread2 = new Thread(testYield, "大熊");
        thread.start();
        thread2.start();
    }
}
