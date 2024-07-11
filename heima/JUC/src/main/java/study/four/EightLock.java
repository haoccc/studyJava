package study.four;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程8锁问题
 */
@Slf4j
public class EightLock {

    public static void main(String[] args) {

    }
}

class Number1{
    public synchronized void a() {
        System.out.println("1");
    }
    public synchronized void b() {
        System.out.println("2");
    }
}
