package study.MethodOfThread;


/**
 * 停止线程：建议正常停止，利用次数，不建议死循环
 * 设置一个标志位
 * 不要使用stop,destroy等过时的方法
 */
public class TestStop implements Runnable{

    // 设置一个停止的标志位
    private boolean flag = true;

    @Override
    public void run() {
        int i=0;
        while (flag){
            System.out.println("run thread:"+i++);
        }
    }

    public void stop(){
        flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i=0;i<1000;i++){
            System.out.println("main:"+i);
            if (i==900){
                testStop.stop();
                System.out.println("线程该停止了");
            }
        }
    }
}
