package study;

//模拟龟兔赛跑

/**
 * 实现Runnable 接口
 * 也可以继承thread类
 * 都需要重写run方法
 */
public class Race implements Runnable {

    private static String winner;

    @Override
    public void run() {
        for(int i=0; i<100; i++){
            boolean flag = false;
            try {
                flag = gameOver(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (flag) break;
//            System.out.println(Thread.currentThread().getName()+"---->跑了:" + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private boolean gameOver(int steps) throws InterruptedException {
        if (winner != null){
            return true;
        } else {
            if (steps == 99){
                winner = Thread.currentThread().getName();
                System.out.println("winner is "+winner);
                return true;
            }
        }
        return false;

    }


    public static void main(String[] args) {
        Race race = new Race();
        Race race2 = new Race();
        Thread thread = new Thread(race, "兔子");
        Thread thread2 = new Thread(race2, "乌龟");
        thread2.start();
        thread.start();

    }
}
