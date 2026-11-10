public class FutureDemo3Service {

    public String firstName() throws InterruptedException {
        try {
            Thread.sleep(2000);
            return "Vinay";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String lastName() {
        try {
            Thread.sleep(2000);
            return "Karthik";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
