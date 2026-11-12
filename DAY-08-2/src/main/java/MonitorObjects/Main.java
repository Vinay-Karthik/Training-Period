package MonitorObjects;

public class Main {
    public static void main(String[] args) {
        Methods methods = new Methods();
        Thread thread1=new Thread(()->methods.method1());
        Thread thread2=new Thread(()->methods.method2());
        Thread thread3=new Thread(()->methods.method3());
        Thread thread4=new Thread(()->methods.method4());

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
