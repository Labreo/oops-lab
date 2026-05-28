package Exp15;

class Message {
    private String text;
    private boolean isSet = false;

    public synchronized void set(String text) {
        while (isSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.text = text;
        isSet = true;
        notify();
    }

    public synchronized void get() {
        while (!isSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumer read: " + this.text);
        isSet = false;
        notify();
    }
}

class Producer extends Thread {
    private Message message;

    public Producer(Message message) {
        this.message = message;
    }

    public void run() {
        message.set("Hello from Producer!");
    }
}

class Consumer extends Thread {
    private Message message;

    public Consumer(Message message) {
        this.message = message;
    }

    public void run() {
        message.get();
    }
}

public class WaitNotifyDemo {
    public static void main(String[] args) {
        Message message = new Message();
        Producer producer = new Producer(message);
        Consumer consumer = new Consumer(message);

        consumer.start();
        producer.start();
    }
}
