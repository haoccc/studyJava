package study.MethodOfThread;


/**
 * 线程分为用户线程和守护线程
 * 虚拟机不用等待守护线程执行完毕
 */
public class TestDaemon{


    public static void main(String[] args) {
        Thread you = new Thread(()->{
            for (int i=0; i<365;i++){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("live");
                }
            System.out.println("==============bye=============");
        });

        Thread god = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("上帝保佑你");
            }
        });
        god.setDaemon(true);

        god.start();
        you.start();

    }
}
