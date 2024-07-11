package study.four;

public class demo1 {

    static int flag = 0;

    public static void main(String[] args) throws InterruptedException {

        test1();

        // synchronized 阻塞式
        System.out.println("阻塞 syn");
        test2();
    }

    /**
     * 多线程竞争出问题例子
     * 临界区 和 竟态条件（race condition）是出问题的原因
     */
    public static void test1() throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 500000; i++) {
                flag ++;
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 500000; i++) {
                flag --;
            }
        });
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(flag);
    }

    /**
     * 枷锁
     * 面向对象改造
     */
    public static void test2() throws InterruptedException {
        Room room = new Room();
        Thread thread = new Thread(()->{
            for (int i = 0; i < 500000; i++) {
                room.increment();
            }
        });

        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 500000; i++) {
                room.decrement();
            }
        });
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(room.getFlag());
    }

}

/**
 * 面向对象改造
 */
class Room{
    private int flag = 0;

    public void increment(){
        synchronized (this){
            flag ++;
        }
    }

    public void decrement(){
        synchronized (this){
            flag --;
        }
    }

    public int getFlag(){
        return flag;
    }
}
