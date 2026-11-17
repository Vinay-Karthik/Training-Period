package service;

public class ThreadDemoService extends Thread {

    @Override
    public void run() {
        System.out.println("Thread" + Thread.currentThread().getName() + " started");
    }

    public static class FutureDemo3Service {

        public String firstName() throws InterruptedException {
            try {
                sleep(2000);
                return "Vinay";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        public String lastName() {
            try {
                sleep(2000);
                return "Karthik";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
