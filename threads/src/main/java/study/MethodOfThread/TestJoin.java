package study.MethodOfThread;


/**
 * 插队 合并线程，待次线程结束再执行其他线程，其他线程阻塞
 * 理解为插队
 */
public class TestJoin implements Runnable{
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
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin, "a");
        thread.start();

        for (int i=0;i<200;i++){
            if (i==100){
                System.out.println(thread.getState());
                thread.join();
            }
            System.out.println("main-->"+i);
        }
    }
}
