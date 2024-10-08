package study.four;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

@Slf4j
public class ExerciseSell {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int x = once();
            if (x != 1000){
                System.out.println(x);
                break;
            }
        }
    }

    public static int once() {
        TicketWindow ticketWindow = new TicketWindow(1000);
        List<Thread> list = new ArrayList<>();
        // 用来存储买出去多少张票
        List<Integer> sellCount = new Vector<>();
        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(() -> {
                // 分析这里的竞态条件
                int count = ticketWindow.sell(randomAmount());
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                sellCount.add(count);
            });
            list.add(t);
            t.start();
        }
        list.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 买出去的票求和
        log.info("selled count:{}",sellCount.stream().mapToInt(c -> c).sum());
        // 剩余票数
        log.info("remainder count:{}", ticketWindow.getCount());
        System.out.println("selled count:" + sellCount.stream().mapToInt(c -> c).sum());
        System.out.println("remainder count: " + ticketWindow.getCount());
        return sellCount.stream().mapToInt(c -> c).sum();
    }

    // Random 为线程安全
    static Random random = new Random();
    // 随机 1~5
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }
}
class TicketWindow {
    private int count;
    public TicketWindow(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }
    public int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        } else {
            return 0;
        }
    }
}
