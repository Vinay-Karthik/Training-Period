package PingPong;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore A = new Semaphore(1);
        Semaphore B = new Semaphore(0);
        Semaphore C=new Semaphore(0);
        int max=10;

        Thread thread1=new Thread(()->{
            for(int i=1;i<=max;i++){
                try {
                    Thread.sleep(50);
                    A.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("A ->"+Thread.currentThread().getName());
                B.release();
            }
        });

        Thread thread2=new Thread(()->{
            for(int i=0;i<max;i++){
                try {
                    Thread.sleep(50);
                    B.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("B ->"+Thread.currentThread().getName());
                C.release();
            }
        });

        Thread thread3=new Thread(()->{
            for(int i=0;i<max;i++){
                try {
                    Thread.sleep(50);
                    C.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("C ->"+Thread.currentThread().getName());
                A.release();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();


    }
}
