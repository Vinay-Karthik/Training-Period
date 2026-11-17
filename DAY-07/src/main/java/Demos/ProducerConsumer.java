package Demos;

public class ProducerConsumer {

    private int value;
    private boolean available = false;

    // I have learned that private cant be declared in the main method because
    // normally the scope of the code is controlled inside method itself so there is no use of
    // making using modifiers inside the method
    public static void main(String[] args) {
        ProducerConsumer data = new ProducerConsumer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    data.produce(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    data.consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }

    public synchronized void produce(int number) throws InterruptedException {
        while (available) {
            wait();
        }
        value = number;
        available = true;
        System.out.println("Produced: " + value);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while (!available) {
            wait();
        }
        System.out.println("Consumed: " + value);
        available = false;
        notifyAll();
    }

}

