package Exp15;

class Counter {
    int count = 0;

    public void incrementUnsync() {
        count++;
    }

    public synchronized void incrementSync() {
        count++;
    }
}

public class CounterDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter1.incrementUnsync();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter1.incrementUnsync();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Count without synchronization: " + counter1.count);

        Counter counter2 = new Counter();

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter2.incrementSync();
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter2.incrementSync();
            }
        });

        t3.start();
        t4.start();
        t3.join();
        t4.join();

        System.out.println("Count with synchronization: " + counter2.count);
    }
}
