package MonitorObjects;

public class Methods {
    public synchronized void method1() {
        try {
            Thread.sleep(500);
            System.out.println("method1");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public synchronized void method2() {
        try {
            Thread.sleep(500);
            System.out.println("method2");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }    }

    public synchronized void method3() {
        try {
            Thread.sleep(500);
            System.out.println("method3");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }    }

    public synchronized void method4() {
        try {
            Thread.sleep(500);
            System.out.println("method4");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }    }
}
