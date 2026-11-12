package MonitorObjects;

public class Methods {
    final Object lock1 = new Object();
    final Object lock2 = new Object();

    final Object lock3 = new Object();

    final Object lock4 = new Object();


    public void method1() {
        synchronized (lock1) {
            try {
                Thread.sleep(1000);
                System.out.println("method1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void method2() {
        synchronized (lock2) {

            try {
                Thread.sleep(1000);
                System.out.println("method2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void method3() {
        synchronized (lock3) {


            try {
                Thread.sleep(1000);
                System.out.println("method3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void method4() {
        synchronized (lock4) {


            try {
                Thread.sleep(1000);
                System.out.println("method4");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
