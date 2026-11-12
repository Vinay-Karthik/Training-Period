package PingPong;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore ping = new Semaphore(1);
        Semaphore pong = new Semaphore(0);
        int max=10;

        Thread thread1=new Thread(()->{
            for(int i=1;i<=max;i++){
                try {
                    Thread.sleep(50);
                    ping.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("ping->"+Thread.currentThread().getName());
                pong.release();
            }
        });

        Thread thread2=new Thread(()->{
            for(int i=0;i<max;i++){
                try {
                    Thread.sleep(50);
                    pong.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("pong ->"+Thread.currentThread().getName());
                ping.release();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
