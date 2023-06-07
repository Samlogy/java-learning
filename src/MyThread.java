public class MyThread extends Thread {

    // Threading implementation in Java:
    // extend: Thread class and --> overrrid run() method
    // implement: Runnable interface and  --> overrrid run() method

    private String threadName;
    public static void main(String[] args) {
        MyThread t1 = new MyThread("T1");
        MyThread t2 = new MyThread("T2");
        MyThread t3 = new MyThread("T3");

        t1.start();
        t2.start();
        t3.start();
    }

    public MyThread(String threadName) {
        this.threadName = threadName;
    }
    @Override
    public void run() {
        System.out.println("Thread " + threadName + " is running...");
        try {
            // Simulate some task being executed
            for (int i = 1; i <= 5; i++) {
                System.out.println(threadName + ": " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " finished execution.");
    }
}
