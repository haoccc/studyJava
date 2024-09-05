package study.ThreadPool;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义线程池
 * 线程池（任务消费者）、阻塞队列、任务生产者
 */

@Slf4j(topic = "c.TestPool")
public class MyThreadPool {
    public static void main(String[] args) {

        // 死等策略
//        final ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MILLISECONDS, 10,
//                BlockingQueue::put);

        final ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.MILLISECONDS, 10, (blockingQueue, task) -> {
            // 超时等待
//            blockingQueue.offer(task, 500, TimeUnit.MILLISECONDS);

            // 抛出异常
            throw new RuntimeException("任务执行失败" + task);
        });

        for (int i = 0; i < 15; i++) {
            int finalI = i;
            threadPool.execute(()->{
                log.info("{}", finalI);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }
}

@Slf4j(topic = "BlockingQueue")
class BlockingQueue<T> {
    //1. 任务队列
    private Deque<T> queue = new ArrayDeque<>();

    //2. 锁
    private ReentrantLock lock = new ReentrantLock();

    //3. 消费者的条件变量
    private Condition emptyWaitSet = lock.newCondition();
    //4. 生产者的条件变量
    private Condition fullWaitSet = lock.newCondition();

    // 5. 容量
    private int capcity;

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    /**
     * 带超时的阻塞获取
     * @return
     */
    public T poll(long timeout, TimeUnit uint){
        // 将时间统一转换成纳秒
        long nanos = uint.toNanos(timeout);

        lock.lock();
        try {
            // 队列 空的需要阻塞等待
            while (queue.isEmpty()){

                // 返回剩余时间
                // 避免虚假唤醒之后等待时间重新刷新
                nanos = emptyWaitSet.awaitNanos(nanos);
                if (nanos <= 0){
                    // 已经超时
                    return null;
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }


    // 阻塞获取
    public T take(){
        lock.lock();
        try {
            // 队列 空的需要阻塞等待
            while (queue.isEmpty()){
                emptyWaitSet.await();
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    // 带超时的阻塞添加
    public boolean offer(T element, long timeout, TimeUnit timeUnit){
        long nanos = timeUnit.toNanos(timeout);
        lock.lock();
        try {
            while (queue.size() == capcity){
                log.info("任务队列满了,需要等待......");
                nanos = fullWaitSet.awaitNanos(nanos);
                if (nanos <=0 ) {
                    log.info("等待超时......");
                    return false;
                }
            }

            log.info("加入任务队列 {}", element);
            queue.addLast(element);
            emptyWaitSet.signal();
            return true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    // 阻塞添加
    public void put(T element){

        lock.lock();
        try {
            while (queue.size() == capcity){
                log.info("任务队列满了,需要等待......");
                fullWaitSet.await();
            }

            log.info("加入任务队列 {}", element);
            queue.addLast(element);
            emptyWaitSet.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public int size(){
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            if (queue.size() == capcity){
                rejectPolicy.reject(this, task);
            } else {
                log.info("加入任务队列 {}", task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 策略模式
 * 拒绝策略
 * @param <T>
 */
@FunctionalInterface
interface RejectPolicy<T>{
    void reject(BlockingQueue<T> blockingQueue, T task);
}


@Slf4j(topic = "ThreadPool")
class ThreadPool{
    // 任务队列
    private BlockingQueue<Runnable> taskQueue;

    // 线程集合
    private HashSet<Worker> workers = new HashSet<>();

    // 线程数量
    private final int coreSize;

    // 获取任务的超时时间
    private long timeout;
    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int capcity, RejectPolicy<Runnable> rejectPolicy) {
        this.taskQueue = new BlockingQueue<>(capcity);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }


    // 执行任务
    public void execute(Runnable task){
        // 当任务数没有超过核心coreSize时，直接交给worker对象执行
        synchronized (workers){
            if (workers.size() < coreSize){
                Worker worker = new Worker(task);
                log.info("新增worker{} --- {}", worker, task);
                workers.add(worker);
                worker.start();
            } else { // 超过加入任务队列
                /**
                 * 拒绝策略
                 * 1.死等
                 * 2.带超时等待
                 * 3.放弃执行任务
                 * 4.抛出异常
                 * 5.调用者自己执行任务
                 */
                taskQueue.tryPut(rejectPolicy, task);
            }
        }

    }


    class Worker extends Thread{
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run(){
            // 执行任务
            // 1 task不为空，执行任务
            // 2 task空，从任务队列获取任务并执行
            while (task !=null || (task = taskQueue.poll(timeout, timeUnit)) != null){
                try{
                    log.info("当前worker {}, 正在执行任务对象 {}", this, task);
                    task.run();
                } catch (Exception e){

                } finally {
                    task = null;
                }
            }

            synchronized (workers){
                log.info("worker 被移除 {}", this);
                workers.remove(this);
            }
        }

    }
}