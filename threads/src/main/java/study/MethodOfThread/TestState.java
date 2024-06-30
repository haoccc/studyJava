package study.MethodOfThread;


import java.sql.SQLOutput;

/**
 * 插队 合并线程，待次线程结束再执行其他线程，其他线程阻塞
 * 理解为插队
 *
 * 线程状态 new 就绪RUNNABLE 阻塞TIMED_WAITING 运行 结束TERMINATED
 * NEW RUNNABLE LOCKED WAITING TIMED_WAITING TERMINATED
 */
public class TestState implements Runnable{
    @Override
    public void run(){
        for(int i =0;i<100; i++){
            System.out.println("vip:"+i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("//////////");
                }
        });

        //观察状态
        Thread.State state = thread.getState();
        System.out.println(state);  // new

        // 观察启动后
        thread.start();
        state = thread.getState();
        System.out.println(state);  // new

        while (state != Thread.State.TERMINATED){   // 线程不停止，一直输出状态
            Thread.sleep(100);
            state = thread.getState();
            System.out.println(state);
        }

    }
}
