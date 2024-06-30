package study.Lock;


import java.util.List;

/**
 * 互相抱着对方需要的资源，形成僵持
 */
public class DeadLock {
}

//口红
class Lipstick{

}
class Mirror{

}

class MakeUp implements Runnable{

    static private Lipstick lipstick = new Lipstick();
    static private Mirror mirror = new Mirror();

    int choice;
    String girlName;

    MakeUp(int choice, String girlName){
        this.choice = choice;
        this.girlName = girlName;
    }


    @Override
    public void run() {
        // 不死锁
        try {
            mkSafe();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("========上面不死锁========");

        try {
            mk();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void mk() throws InterruptedException {
        if (choice==0){
            synchronized (lipstick){
                System.out.println(this.girlName + "获得口红的锁");
                Thread.sleep(1000);
                synchronized (mirror){
                    System.out.println(this.girlName + "获得镜子的锁");
                }
            }
        }else {
            synchronized (mirror){
                System.out.println(this.girlName + "获得镜子的锁");
                Thread.sleep(2000);
                synchronized (lipstick){
                    System.out.println(this.girlName + "获得口红的锁");
            }
            }
        }
    }


    /**
     * 不死锁
     */
    private void mkSafe() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.girlName + "获得口红的锁");
                Thread.sleep(1000);
            }
            synchronized (mirror) {
                System.out.println(this.girlName + "获得镜子的锁");
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.girlName + "获得镜子的锁");
                Thread.sleep(2000);
            }
            synchronized (lipstick) {
                System.out.println(this.girlName + "获得口红的锁");
            }
        }
    }

    public static void main(String[] args) {
        MakeUp makeUp = new MakeUp(1, "a");
        MakeUp makeUp2 = new MakeUp(0, "b");

        new Thread(makeUp).start();
        new Thread(makeUp2).start();
    }
}
