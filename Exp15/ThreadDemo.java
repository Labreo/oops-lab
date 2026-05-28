package Exp15;

public class ThreadDemo extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hello from Thread!");
        }
    }

    public static void main(String[] args) {
        System.out.println("Main thread running");
        ThreadDemo childThread = new ThreadDemo();
        childThread.start();
        System.out.println("Main thread running");
    }
}
