package Singleton_Pattern;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//         creating new instance each time when we are calling
//        Thread thread2 = new Thread(() -> {
//            NormalInstance normalInstance1 = NormalInstance.getInstance();
//            System.out.println(normalInstance1);
//        });
//        thread2.start();
//        Thread.sleep(1000);
//        Thread thread1 = new Thread(() -> {
//            NormalInstance normalInstance2 = NormalInstance.getInstance();
//            System.out.println(normalInstance2);
//        });
//        thread1.start();


        Thread thread2 = new Thread(() -> {
            ThreadSafeSingleton threadSafeSingleton1 = ThreadSafeSingleton.getInstance();
            System.out.println(threadSafeSingleton1);
        });
        thread2.start();
        Thread.sleep(1000);
        Thread thread1 = new Thread(() -> {
            ThreadSafeSingleton threadSafeSingleton2 = ThreadSafeSingleton.getInstance();
            System.out.println(threadSafeSingleton2);
        });
        thread1.start();
    }
}
