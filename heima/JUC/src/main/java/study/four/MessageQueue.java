package study.four;

import java.util.LinkedList;

public class MessageQueue {
    private LinkedList<Message> queue = new LinkedList<>();
    private int size = 10;

    public Message get() throws InterruptedException {
        synchronized (queue){
            while (queue.isEmpty()){
                queue.wait();
            }
            queue.notifyAll();
            return queue.removeFirst();
        }
    }

    public void put(Message message) throws InterruptedException {
        synchronized (queue){
            while (queue.size() >= size){
                System.out.println("队列满了");
                queue.wait();
            }
            queue.addLast(message);
            queue.notifyAll();
            System.out.println("请消费：" + message.getId() + message.getMessage());
        }
    }

    public static void main(String[] args) {
        MessageQueue messageQueue  = new MessageQueue();

    }
}


class Message {
    private int id;
    private String message;

    public Message(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public Message(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
