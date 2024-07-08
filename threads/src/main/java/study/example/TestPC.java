package study.example;

import java.util.IllegalFormatCodePointException;
import java.util.Random;

public class TestPC {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        new Product(synContainer).start();
        new Product(synContainer).start();
        new Consumer(synContainer).start();
        new Consumer(synContainer).start();
        new Product(synContainer).start();
        new Product(synContainer).start();
        new Consumer(synContainer).start();
        new Consumer(synContainer).start();


    }
}


/**
 * 生产者
 */
class Product extends Thread{
    static Integer i=0;
    SynContainer synContainer;
    public Product(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        while (i<100){
            try {
                product(i++);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void product(int i) throws InterruptedException {
//        Chicken chicken = new Chicken(i);
        synContainer.push(i);
    }
}


class Consumer extends Thread{
    SynContainer synContainer;
    static Integer i=0;

    public Consumer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (; i<100; ){
            try {
//                this.sleep(Random);
                cos(i++);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void cos(int i) throws InterruptedException {
//        Chicken chicken = new Chicken(i);
//        System.out.println("-----" + i + "-----只鸡");
        synContainer.pop(i);
    }

}


class Chicken{
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

class SynContainer{
    Chicken[] chickens = new Chicken[10];
    int count = 0;
    Random random = new Random();


    public synchronized void push(int i) throws InterruptedException {
        while (count == chickens.length){
            // 队列满了， 通知消费
            this.wait();
        }
        // 可以继续生产
        Chicken chicken = new Chicken(i);
        System.out.println("生-----" + i + "-----只鸡");
        chickens[count] = chicken;
        count ++;
        Thread.sleep(random.nextInt(200));
        this.notifyAll();

    }

    public synchronized void pop(int i) throws InterruptedException {
        while (count == 0){
            // 队列空了， 通知生产
            this.wait();
        }
        // 可以继续消费
        System.out.println("-----" + i + "-----只鸡");
        count --;
        this.notifyAll();

    }
}