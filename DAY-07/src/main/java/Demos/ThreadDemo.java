package Demos;

import service.ThreadDemoService;

public class ThreadDemo {
    public static void main(String[] args) {
//        Runnable runnable=()->{
//            System.out.println("Thread :"+ Thread.currentThread().getName());
//        };
//        Thread thread1 = new Thread(runnable);
//
//        Thread thread2 = new Thread(runnable);
//
//        // if you want to use similar task for multiple threads then we can use a runnable task for it
//
//        thread1.start();
//        thread2.start();

        ThreadDemoService thread1 = new ThreadDemoService();
        ThreadDemoService thread2 = new ThreadDemoService();
        thread1.start();
        thread2.start();
    }
}
